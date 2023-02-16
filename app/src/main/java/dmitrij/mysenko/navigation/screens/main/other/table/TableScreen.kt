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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.shared.CurrentRoute
import kotlinx.coroutines.launch

@Composable
fun TableScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        CurrentRoute(navController = navController)
        Spacer(modifier = Modifier.height(20.dp))

        val items by remember { mutableStateOf(generateData()) }

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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(color = Color.LightGray)
                .scrollable(scrollState, Orientation.Vertical, flingBehavior = ScrollableDefaults.flingBehavior())
        ) {
//            val k1 by remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }
//            val k2 by remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset } }
//            LaunchedEffect(
//                key1 = k1,
//                key2 = k2
//            ){
//                scope.launch {
//                    listState.scrollToItem(
//                        index = lazyListState.firstVisibleItemIndex,
//                        scrollOffset = lazyListState.firstVisibleItemScrollOffset
//                    )
//                }
//            }

            LazyColumn(
                state = listState,
                verticalArrangement = Arrangement.spacedBy(5.dp),
                userScrollEnabled = false,
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight()
            ) {
                items(
                    items = items,
                    key = { it.rowName }
                ) { item ->
                    RowName(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(color = Color.Magenta),
                        item = item.rowName
                    )
                }
            }
            LazyColumn(
                state = lazyListState,
                verticalArrangement = Arrangement.spacedBy(5.dp),
                userScrollEnabled = false,
                modifier = Modifier
                    .fillMaxHeight()
                    .horizontalScroll(rememberScrollState())
            ) {
                items(
                    items = items,
                    key = { it.rowName }
                ) { item ->
                    RowContent(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(color = Color.Cyan),
                        item = item
                    )
                }
            }
        }
    }
}

@Composable
fun RowName(
    modifier: Modifier,
    item: String
) {
    Box(modifier = modifier) {
        Text(text = item, modifier = Modifier.align(alignment = Alignment.Center))
    }
}

@Composable
fun RowContent(
    modifier: Modifier,
    item: TableItem
){
    Row(modifier = modifier) {
        RowName(modifier = Modifier
            .width(150.dp)
            .fillMaxHeight(), item = item.cell1)
        RowName(modifier = Modifier
            .width(150.dp)
            .fillMaxHeight(), item = item.cell2)
        RowName(modifier = Modifier
            .width(150.dp)
            .fillMaxHeight(), item = item.cell3)
        RowName(modifier = Modifier
            .width(150.dp)
            .fillMaxHeight(), item = item.cell4)
    }
}

data class TableItem(
    val rowName: String,
    val cell1: String,
    val cell2: String,
    val cell3: String,
    val cell4: String,
)

private fun generateData(): List<TableItem> {
    val list = mutableListOf<TableItem>()
    repeat(20) { i ->
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
