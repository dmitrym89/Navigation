package dmitrij.mysenko.navigation.screens.aftermain

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.shared.CurrentRoute

@Composable
fun AfterMainScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        CurrentRoute(navController = navController)
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.TabsScreen.route) { popUpTo(Screen.MainScreen.route) }
                navController.navigate(Screen.CustomTabsScreen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Back to MainScreen and forward to CustomTabScreen")
        }

        Spacer(modifier = Modifier.height(50.dp))

        val list = mutableListOf<MyItem>()
        repeat(15) { i ->
            list.add(MyItem(i, "item$i"))
        }
        MyGrid(
            list = list,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(color = Color.Blue)
        )

    }
}

private data class MyItem(
    val id: Int,
    val stroke: String
)

@Composable
private fun RowScope.SingleItem(item: MyItem) {
    Text(
        text = item.stroke,
        modifier = Modifier
            .padding(5.dp)
            .weight(1f)
            .background(color = Color.LightGray)
            .padding(10.dp)
    )
}

@Composable
private fun TwoItems(item1: MyItem, item2: MyItem?) {
    Row(modifier = Modifier.fillMaxWidth()) {
        SingleItem(item = item1)
        if (item2 == null) {
            Spacer(modifier = Modifier.weight(1f))
        } else {
            SingleItem(item = item2)
        }
    }
}

@Composable
private fun MyGrid(
    list: List<MyItem>,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .padding(5.dp)
            .verticalScroll(scrollState)
    ) {
        repeat((list.size + 1) / 2) { lineNumber ->
            TwoItems(
                item1 = list[lineNumber * 2],
                item2 = if (list.size != lineNumber * 2 + 1) {
                    list[lineNumber * 2 + 1]
                } else {
                    null
                }
            )
        }
    }
}

@Composable
private fun DropDownList(
    valuesList: List<String>,
    valueToSelect: String,
    selectedValue: (String) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box {
        Row(
            modifier = Modifier.clickable { expanded = !expanded },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = valueToSelect,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(256.dp)
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                tint = MaterialTheme.colors.onBackground,
                contentDescription = null
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            valuesList.forEach { value ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        selectedValue(value)
                    }
                ) {
                    Text(text = value, textAlign = TextAlign.Center)
                }
            }
        }
    }
}


