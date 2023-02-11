package dmitrij.mysenko.navigation.screens.main.other.draganddrop.simple

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.R
import dmitrij.mysenko.navigation.shared.CurrentRoute
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun SimpleDragAndDropScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        CurrentRoute(navController = navController)
        Spacer(modifier = Modifier.height(50.dp))

        val data = mutableListOf<ListItem>()
        repeat(20) {
            data.add(ListItem(it, "item_$it"))
        }
        val list = data.toList()

        DragDropList(items = list, onMove = { from, to -> data.move(from, to) })

//        val lazyListState = rememberLazyListState()
//        var draggedOffset by remember { mutableStateOf(0f) }
//        var draggedItem by remember { mutableStateOf<LazyListItemInfo?>(null) }
//        var currentIndexOfDraggedItem by remember { mutableStateOf<Int?>(null) }
//
//        LazyColumn(
//            state = lazyListState,
//            contentPadding = PaddingValues(start = 10.dp, end = 10.dp, bottom = 60.dp),
//            modifier = Modifier
//                .fillMaxSize()
//                .weight(1f)
//                .pointerInput(Unit) {
//                    detectDragGesturesAfterLongPress(
//                        onDrag = { change, offset ->
//                            change.consume()
//                            draggedOffset += offset.y
//
//                            draggedItem?.let {
//                                val startOffset = it.offset + draggedOffset
//                                val endOffset = it.offset + it.size + draggedOffset
//                            }
//                        },
//                        onDragStart = { offset ->
//                            lazyListState.layoutInfo.visibleItemsInfo
//                                .firstOrNull { item -> offset.y.toInt() in item.offset..item.offset + item.size }
//                                ?.also {
//                                    currentIndexOfDraggedItem = it.index
//                                    draggedItem = it
//                                }
//                        },
//                        onDragEnd = {
//                            currentIndexOfDraggedItem = null
//                            draggedOffset = 0f
//                        },
//                        onDragCancel = {
//                            currentIndexOfDraggedItem = null
//                            draggedOffset = 0f
//                        }
//                    )
//                }
//        ) {
//            itemsIndexed(
//                items = list,
////                key = { index, item ->
////                    item.id
////                }
//            ) { index, item ->
//                Text(
//                    text = item.text,
//                    fontSize = 20.sp,
//                    modifier = Modifier
//                        .graphicsLayer {
//                            translationY =
//                                if (index == currentIndexOfDraggedItem) draggedOffset else 0f
//                        }
//                        .fillMaxWidth()
//                        .padding(5.dp)
//                        .background(color = Color.LightGray)
//                        .padding(10.dp)
//
//                )
//            }
//        }
    }
}

data class ListItem(
    val id: Int,
    val text: String
)

@Composable
fun DragDropList(
    items: List<ListItem>,
    onMove: (Int, Int) -> Unit,
    modifier: Modifier = Modifier
) {

    val scope = rememberCoroutineScope()

    var overscrollJob by remember { mutableStateOf<Job?>(null) }

    val dragDropListState = rememberDragDropListState(onMove = onMove)
    LazyColumn(
        modifier = modifier
            .pointerInput(Unit) {
                detectDragGesturesAfterLongPress(
                    onDrag = { change, offset ->
                        change.consume()
                        dragDropListState.onDrag(offset)

                        if (overscrollJob?.isActive == true)
                            return@detectDragGesturesAfterLongPress

                        dragDropListState.checkForOverScroll()
                            .takeIf { it != 0f }
                            ?.let {
                                overscrollJob =
                                    scope.launch { dragDropListState.lazyListState.scrollBy(it) }
                            }
                            ?: run { overscrollJob?.cancel() }
                    },
                    onDragStart = { offset -> dragDropListState.onDragStart(offset) },
                    onDragEnd = { dragDropListState.onDragInterrupted() },
                    onDragCancel = { dragDropListState.onDragInterrupted() }
                )
            },
        state = dragDropListState.lazyListState
    ) {
        itemsIndexed(items) { index, item ->
            Column(
                modifier = Modifier
                    .composed {
                        val offsetOrNull =
                            dragDropListState.elementDisplacement.takeIf {
                                index == dragDropListState.currentIndexOfDraggedItem
                            }

                        Modifier
                            .graphicsLayer {
                                translationY = offsetOrNull ?: 0f
                            }
                    }
                    .background(Color.White, shape = RoundedCornerShape(4.dp))
                    .fillMaxWidth()
            ) {
                Text(
                    text = item.text,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .background(color = Color.LightGray)
                        .padding(10.dp)
                )
            }
        }
    }
}

@Composable
fun rememberDragDropListState(
    lazyListState: LazyListState = rememberLazyListState(),
    onMove: (Int, Int) -> Unit,
): DragDropListState {
    return remember { DragDropListState(lazyListState = lazyListState, onMove = onMove) }
}

class DragDropListState(
    val lazyListState: LazyListState,
    private val onMove: (Int, Int) -> Unit
) {
    var draggedDistance by mutableStateOf(0f)

    var initiallyDraggedElement by mutableStateOf<LazyListItemInfo?>(null)

    var currentIndexOfDraggedItem by mutableStateOf<Int?>(null)

    val initialOffsets: Pair<Int, Int>?
        get() = initiallyDraggedElement?.let { Pair(it.offset, it.offsetEnd) }

    val elementDisplacement: Float?
        get() = currentIndexOfDraggedItem
            ?.let { lazyListState.getVisibleItemInfoFor(absoluteIndex = it) }
            ?.let { item ->
                (initiallyDraggedElement?.offset ?: 0f).toFloat() + draggedDistance - item.offset
            }

    val currentElement: LazyListItemInfo?
        get() = currentIndexOfDraggedItem?.let {
            lazyListState.getVisibleItemInfoFor(absoluteIndex = it)
        }

    var overscrollJob by mutableStateOf<Job?>(null)

    fun onDragStart(offset: Offset) {
        lazyListState.layoutInfo.visibleItemsInfo
            .firstOrNull { item -> offset.y.toInt() in item.offset..(item.offset + item.size) }
            ?.also {
                currentIndexOfDraggedItem = it.index
                initiallyDraggedElement = it
            }
    }

    fun onDragInterrupted() {
        draggedDistance = 0f
        currentIndexOfDraggedItem = null
        initiallyDraggedElement = null
        overscrollJob?.cancel()
    }

    fun onDrag(offset: Offset) {
        draggedDistance += offset.y

        initialOffsets?.let { (topOffset, bottomOffset) ->
            val startOffset = topOffset + draggedDistance
            val endOffset = bottomOffset + draggedDistance

            currentElement?.let { hovered ->
                lazyListState.layoutInfo.visibleItemsInfo
                    .filterNot { item -> item.offsetEnd < startOffset || item.offset > endOffset || hovered.index == item.index }
                    .firstOrNull { item ->
                        val delta = startOffset - hovered.offset
                        when {
                            delta > 0 -> (endOffset > item.offsetEnd)
                            else -> (startOffset < item.offset)
                        }
                    }
                    ?.also { item ->
                        currentIndexOfDraggedItem?.let { current ->
                            onMove.invoke(
                                current,
                                item.index
                            )
                        }
                        currentIndexOfDraggedItem = item.index
                    }
            }
        }
    }

    fun checkForOverScroll(): Float {
        return initiallyDraggedElement?.let {
            val startOffset = it.offset + draggedDistance
            val endOffset = it.offsetEnd + draggedDistance

            return@let when {
                draggedDistance > 0 -> (endOffset - lazyListState.layoutInfo.viewportEndOffset).takeIf { diff -> diff > 0 }
                draggedDistance < 0 -> (startOffset - lazyListState.layoutInfo.viewportStartOffset).takeIf { diff -> diff < 0 }
                else -> null
            }
        } ?: 0f
    }
}

fun LazyListState.getVisibleItemInfoFor(absoluteIndex: Int): LazyListItemInfo? {
    return this.layoutInfo.visibleItemsInfo.getOrNull(absoluteIndex - this.layoutInfo.visibleItemsInfo.first().index)
}

val LazyListItemInfo.offsetEnd: Int
    get() = this.offset + this.size

fun <T> MutableList<T>.move(from: Int, to: Int) {
    if (from == to)
        return

    val element = this.removeAt(from) ?: return
    this.add(to, element)
}