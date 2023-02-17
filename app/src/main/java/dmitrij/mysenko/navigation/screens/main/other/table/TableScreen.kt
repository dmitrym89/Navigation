package dmitrij.mysenko.navigation.screens.main.other.table

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.shared.CurrentRoute
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
fun TableScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        CurrentRoute(navController = navController)
        Spacer(modifier = Modifier.height(20.dp))

        val items by remember { mutableStateOf(generateData(20)) }

        Table(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),
            items = items,
            lockedRow = { item ->
                RowName(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(color = Color.Magenta),
                    item = item.rowName
                )
            },
            tableRow = { item ->
                RowContent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(color = Color.Cyan),
                    item = item
                )
            },
            bottomContent = {
                Text(
                    text = "* here it is written that not everything is so simple",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterStart)
                        .padding(start = 10.dp)
                )
            }
        )
    }
}

@Composable
fun Table(
    modifier: Modifier,
    items: List<TableItem>,
    background: Color = Color.LightGray,
    lockedColumnWidth: Dp = 100.dp,
    verticalArrangement: Dp = 5.dp,
    lockedRow: @Composable (TableItem) -> Unit,
    tableRow: @Composable (TableItem) -> Unit,
    bottomContent: @Composable BoxScope.() -> Unit
) {
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val lazyListState = rememberLazyListState()
    val scrollState = rememberScrollableState { delta ->
        scope.launch {
            listState.scrollBy(-delta)
            lazyListState.scrollBy(-delta)
        }
        delta
    }

    var maxHeight by remember {
        mutableStateOf(0)
    }
    var height by remember {
        mutableStateOf(0)
    }
    var itemHeight by remember {
        mutableStateOf(0)
    }
    var bottomHeight by remember {
        mutableStateOf(0f)
    }
    var offset by remember {
        mutableStateOf(0f)
    }
    var offsetLimit by remember {
        mutableStateOf(0)
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                if (height + bottomHeight > maxHeight) {
                    val coerceMin = minOf(height + bottomHeight - maxHeight, bottomHeight)
                    val last = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()
                    if (
                        last != null
                        && last.index == items.size - 1
                    ) {
                        if (abs(last.offset - offsetLimit) <= 2) {
                            if (available.y <= 0) {
                                offset = (offset + available.y).coerceIn(-coerceMin, 0f)
                            } else {
                                val consumed = offset + available.y
                                offset = (offset + available.y).coerceIn(-coerceMin, 0f)
                                return if (consumed > 0) {
                                    available.copy(y = available.y - consumed)
                                } else {
                                    available
                                }
                            }
                        }
                    } else {
                        offset = 0f
                    }
                }
                return super.onPreScroll(available, source)
            }
        }
    }

    Layout(
        modifier = modifier
            .background(color = background)
            .nestedScroll(nestedScrollConnection)
            .clip(RectangleShape),
        content = {
            Row(
                modifier = modifier
                    .scrollable(
                        state = scrollState,
                        orientation = Orientation.Vertical,
                        flingBehavior = ScrollableDefaults.flingBehavior()
                    )
                    .graphicsLayer {
                        translationY = offset
                    }
            ) {
                LazyColumn(
                    state = listState,
                    verticalArrangement = Arrangement.spacedBy(verticalArrangement),
                    userScrollEnabled = false,
                    modifier = Modifier
                        .width(lockedColumnWidth)
                ) {
                    items(
                        items = items,
                        key = { it.rowName }
                    ) { item ->
                        lockedRow(item)
                    }
                }
                LazyColumn(
                    state = lazyListState,
                    verticalArrangement = Arrangement.spacedBy(verticalArrangement),
                    userScrollEnabled = false,
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                ) {
                    items(
                        items = items,
                        key = { it.rowName }
                    ) { item ->
                        tableRow(item)
                    }
                }
            }
            lockedRow(TableItem())
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        translationY = height + offset
                    }
                    .padding(top = verticalArrangement)
                    .background(color = Color.Red)
            ) {
                bottomContent()
            }
        }
    ) { measurables, constraints ->
        val trueConstraints = constraints.copy(
            minWidth = 0,
            minHeight = 0
        )
        val placeableTable = measurables[0].measure(trueConstraints)
        val placeableForItemSize = measurables[1].measure(trueConstraints)
        val botHeight = placeableForItemSize.height + verticalArrangement.toPx()
        val bottomConstraints =
            trueConstraints.copy(maxHeight = botHeight.toInt())
        val placeableBottom = measurables[2].measure(bottomConstraints)
        maxHeight = constraints.maxHeight
        itemHeight = placeableForItemSize.height
        bottomHeight = botHeight
        val possibleHeight =
            items.size * placeableForItemSize.height + (items.size - 1) * verticalArrangement.toPx()
        height = minOf(placeableTable.height, possibleHeight.toInt())
        offsetLimit = height - itemHeight
        layout(width = constraints.maxWidth, height = constraints.maxHeight) {
            placeableTable.place(0, 0)
            placeableBottom.place(0, 0)
        }
    }
}

@Composable
private fun RowName(
    modifier: Modifier,
    item: String
) {
    Box(modifier = modifier) {
        Text(text = item, modifier = Modifier.align(alignment = Alignment.Center))
    }
}

@Composable
private fun RowContent(
    modifier: Modifier,
    item: TableItem
) {
    Row(modifier = modifier) {
        RowName(
            modifier = Modifier
                .width(150.dp)
                .fillMaxHeight(), item = item.cell1
        )
        RowName(
            modifier = Modifier
                .width(150.dp)
                .fillMaxHeight(), item = item.cell2
        )
        RowName(
            modifier = Modifier
                .width(150.dp)
                .fillMaxHeight(), item = item.cell3
        )
        RowName(
            modifier = Modifier
                .width(150.dp)
                .fillMaxHeight(), item = item.cell4
        )
    }
}

data class TableItem(
    val rowName: String = "",
    val cell1: String = "",
    val cell2: String = "",
    val cell3: String = "",
    val cell4: String = "",
)

private fun generateData(count: Int = 20): List<TableItem> {
    val list = mutableListOf<TableItem>()
    repeat(count) { i ->
        list.add(
            TableItem(
                rowName = "RowName_$i",
                cell1 = "cell1_$i",
                cell2 = "cell2_$i",
                cell3 = "cell3_$i",
                cell4 = "cell4_$i",
            )
        )
    }
    return list
}
