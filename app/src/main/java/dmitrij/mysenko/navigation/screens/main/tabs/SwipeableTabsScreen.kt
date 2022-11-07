package dmitrij.mysenko.navigation.screens.main.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.shared.CurrentRoute
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SwipeableTabsScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        CurrentRoute(navController = navController)
        Spacer(modifier = Modifier.height(20.dp))

        val state = rememberPagerState()
        val scope = rememberCoroutineScope()

        val items = listOf(
            Tab.Tab1,
            Tab.Tab2,
            Tab.Tab3
        )

        TabRow(
            selectedTabIndex = state.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(state, tabPositions)
                )
            }
        ) {
            items.forEachIndexed { index, tab ->
                Tab(
                    selected = index == state.currentPage,
                    onClick = {
                        scope.launch { state.animateScrollToPage(index) }
                    },
                    icon = { Icon(imageVector = tab.icon, contentDescription = null) },
                    text = { Text(text = stringResource(id = tab.resourceId)) }
                )
            }
        }

        HorizontalPager(
            count = items.size,
            state = state,
            modifier = Modifier.weight(1f)
        ) { page ->
            TabData(tab = items[page])
        }
    }
}