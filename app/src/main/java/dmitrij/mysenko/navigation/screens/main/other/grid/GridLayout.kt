package dmitrij.mysenko.navigation.screens.main.other.grid

import androidx.compose.foundation.layout.LayoutScopeMarker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp

@Composable
fun GridLayout(
    modifier: Modifier = Modifier,
    columnCount: Int,
    space: Dp,
    content: @Composable GridScope.() -> Unit
) {
    val occupiedCell = mutableSetOf<Int>()
    var lastCell = 0
    val density = LocalDensity.current.density
    Layout(
        content = { GridScopeInstance.content() },
        modifier = modifier,
    ) { measurables, constraints ->
        val cellSize =
            ((constraints.maxWidth - space.value * density * (columnCount - 1)) / columnCount).toInt()
        val spaceSize = (space.value * density).toInt()

        val itemDataList = measurables.map { it.parentData as? GridItemData }
        val placeables = measurables.mapIndexed { index, measurable ->
            val itemData = itemDataList[index]
            val width = if (itemData != null && itemData.fill) {
                itemData.countColumn * cellSize + (itemData.countColumn - 1) * spaceSize
            } else {
                cellSize
            }
            val height = if (itemData != null && itemData.fill) {
                itemData.countRow * cellSize + (itemData.countRow - 1) * spaceSize
            } else {
                cellSize
            }
            val constr = Constraints(width, width, height, height)
            measurable.measure(constr)
        }

        val items = mutableListOf<ItemForPlace>()
        for (i in placeables.indices) {
            lastCell = findNextFreeCell(occupiedCell, lastCell)
            occupiedCell.add(lastCell)
            val itemData = itemDataList[i]
            if (itemData != null) {
                if (itemData.countColumn > 1 && itemData.countRow > 1) {
                    repeat(itemData.countRow) { y ->
                        repeat(itemData.countColumn) { x ->
                            occupiedCell.add(lastCell + y * columnCount + x)
                        }
                    }
                } else {
                    if (itemData.countColumn > 1) {
                        repeat(itemData.countColumn) {
                            occupiedCell.add(lastCell + it)
                        }
                    }
                    if (itemData.countRow > 1) {
                        repeat(itemData.countRow) {
                            occupiedCell.add(lastCell + it * columnCount)
                        }
                    }
                }
            }
            val row = lastCell / 4
            val column = lastCell % 4
            items.add(
                ItemForPlace(
                    x = column * (cellSize + spaceSize),
                    y = row * (cellSize + spaceSize),
                    placeable = placeables[i]
                )
            )
        }
        val lines = (occupiedCell.max() + columnCount) / 4
        val layoutHeight = cellSize * lines + spaceSize * (lines - 1)
        layout(constraints.maxWidth, layoutHeight) {
            items.forEach { item ->
                item.placeable.place(item.x, item.y)
            }
        }
    }
}

fun findNextFreeCell(occupiedCell: Set<Int>, lastCell: Int): Int {
    var lc = lastCell
    while (occupiedCell.contains(lc)) {
        lc++
    }
    return lc
}

data class ItemForPlace(
    val x: Int,
    val y: Int,
    val placeable: Placeable
)

@LayoutScopeMarker
@Immutable
interface GridScope {

    @Stable
    fun Modifier.rowSpan(
        count: Int = 1,
        fill: Boolean = true
    ): Modifier

    @Stable
    fun Modifier.columnSpan(
        count: Int = 1,
        fill: Boolean = true
    ): Modifier
}

object GridScopeInstance : GridScope {

    @Stable
    override fun Modifier.rowSpan(count: Int, fill: Boolean): Modifier {
        return this.then(GridItemData(countRow = count, fill = fill))
    }

    @Stable
    override fun Modifier.columnSpan(count: Int, fill: Boolean): Modifier {
        return this.then(GridItemData(countColumn = count, fill = fill))
    }
}

data class GridItemData(
    val countColumn: Int = 1,
    val countRow: Int = 1,
    val fill: Boolean,
) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?): GridItemData {
        val data = parentData as? GridItemData
        if (data == null) {
            return this@GridItemData
        } else {
            return GridItemData(
                maxOf(countColumn, data.countColumn),
                maxOf(countRow, data.countRow),
                fill || data.fill
            )
        }
    }
}

