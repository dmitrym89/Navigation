package dmitrij.mysenko.navigation.screens.main.other.collapsing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.shared.CurrentRoute

@Composable
fun CollapsingHalfVisibleRectScreen(navController: NavController){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().statusBarsPadding()
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        CurrentRoute(navController = navController)
        Spacer(modifier = Modifier.height(20.dp))

        val data = mutableListOf<ListItem>()
        repeat(20) {
            data.add(ListItem(it, "item_$it"))
        }
        Collapsing(data = data.toList())
    }
}

@Composable
fun ColumnScope.Collapsing(data: List<ListItem>) {
    val listState = rememberLazyListState()

    var index by remember {
        mutableStateOf(-1)
    }
    var halfHeight by remember {
        mutableStateOf(-1)
    }
    var delta by remember {
        mutableStateOf(-1)
    }
    val progress by remember {
        derivedStateOf(
            policy = structuralEqualityPolicy()
        ) {
            if (index == -1 && listState.layoutInfo.viewportSize.height != 0) {
                halfHeight = listState.layoutInfo.viewportSize.height / 2
                listState.layoutInfo.visibleItemsInfo.forEach { lazyListItemInfo ->
                    if (index == -1 && lazyListItemInfo.offset > halfHeight) {
                        index = lazyListItemInfo.index
                        delta = lazyListItemInfo.offset - halfHeight
                    }
                }
            }
            val item = listState.layoutInfo.visibleItemsInfo.find { it.index == index }
            if (item != null) {
                val p = (item.offset - delta).toFloat() / halfHeight
                if (p > 0) {
                    p
                } else {
                    0f
                }
            } else {
                0f
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .alpha(progress)
            .background(color = Color.Blue)
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
        state = listState
    ) {
        items(items = data, key = { it.id }) { item ->
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

data class ListItem(
    val id: Int,
    val text: String
)