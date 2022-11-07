package dmitrij.mysenko.navigation.screens.main.tabs

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.shared.CurrentRoute
import dmitrij.mysenko.navigation.R

@Composable
fun DefaultTabsScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        CurrentRoute(navController = navController)
        Spacer(modifier = Modifier.height(20.dp))

        val state = remember {
            mutableStateOf(0)
        }

        val items = listOf(
            Tab.Tab1,
            Tab.Tab2,
            Tab.Tab3
        )

        TabRow(selectedTabIndex = state.value) {
            items.forEachIndexed { index, tab ->
                Tab(
                    selected = index == state.value,
                    onClick = { state.value = index },
                    icon = { Icon(imageVector = tab.icon, contentDescription = null)},
                    text = { Text(text = stringResource(id = tab.resourceId))}
                )
            }
        }

        TabData(tab = items[state.value], modifier = Modifier.weight(1f))
    }
}

@Composable
fun TabData(
    tab: Tab,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Icon(imageVector = tab.icon, contentDescription = null)
        Text(text = stringResource(id = tab.resourceId))
    }
}

sealed class Tab(val icon: ImageVector, @StringRes val resourceId: Int) {
    object Tab1 : Tab(Icons.Filled.Favorite, R.string.tab_1)
    object Tab2 : Tab(Icons.Filled.Call, R.string.tab_2)
    object Tab3 : Tab(Icons.Filled.Close, R.string.tab_3)
}