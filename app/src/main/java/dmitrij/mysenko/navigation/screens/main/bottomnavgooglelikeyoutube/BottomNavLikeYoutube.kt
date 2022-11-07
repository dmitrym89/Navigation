package dmitrij.mysenko.navigation.screens.main.bottomnavgooglelikeyoutube


import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dmitrij.mysenko.navigation.R
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.screens.main.bottomnavgoogle.*
import dmitrij.mysenko.navigation.shared.BottomSheetContentMoreThenHalfScreen

sealed class BottomNavItemLikeYoutube(
    val screen: Screen,
    val icon: ImageVector,
    @StringRes val resourceId: Int,
    val graph: NestedGraph
) {
    object BottomNavItem1 : BottomNavItemLikeYoutube(
        Screen.BottomNavYoutubeScreen11,
        Icons.Filled.Favorite,
        R.string.bottom_nav_item_1,
        NestedGraph.BottomNavYoutube1
    )

    object BottomNavItem2 : BottomNavItemLikeYoutube(
        Screen.BottomNavYoutubeScreen21,
        Icons.Filled.AccountBox,
        R.string.bottom_nav_item_2,
        NestedGraph.BottomNavYoutube2
    )

    object BottomNavItem3 : BottomNavItemLikeYoutube(
        Screen.BottomNavYoutubeScreen31,
        Icons.Filled.List,
        R.string.bottom_nav_item_3,
        NestedGraph.BottomNavYoutube3
    )

    object BottomNavItem4 : BottomNavItemLikeYoutube(
        Screen.BottomNavYoutubeScreen41,
        Icons.Filled.Call,
        R.string.bottom_nav_item_4,
        NestedGraph.BottomNavYoutube4
    )
}

@Composable
fun BottomNavYoutubeScreen(rootNavController: NavController) {

    val navHostController = rememberNavController()

    val systemUiController = rememberSystemUiController()
    val color = MaterialTheme.colors.primarySurface

    DisposableEffect(systemUiController) {
        systemUiController.setNavigationBarColor(
            color = color,
            darkIcons = false
        )
        onDispose {
            systemUiController.setNavigationBarColor(
                color = Color(0x88000000),
                darkIcons = false
            )
        }
    }

    val items = listOf(
        BottomNavItemLikeYoutube.BottomNavItem1,
        BottomNavItemLikeYoutube.BottomNavItem2,
        BottomNavItemLikeYoutube.BottomNavItem3,
        BottomNavItemLikeYoutube.BottomNavItem4,
    )

    val tabStack = remember {
        mutableStateListOf<NestedGraph>(NestedGraph.BottomNavYoutube1)
    }

    Scaffold(
        bottomBar = {
            BottomNavigation(modifier = Modifier.navigationBarsPadding()) {
                val navBackStackEntry by navHostController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { bottomNavItem ->
                    BottomNavigationItem(
                        icon = { Icon(bottomNavItem.icon, contentDescription = null) },
                        label = { Text(stringResource(bottomNavItem.resourceId)) },
                        selected = currentDestination?.hierarchy?.any { it.route == bottomNavItem.graph.route } == true,
                        onClick = {
                            if (tabStack.last() != bottomNavItem.graph) {
                                tabStack.remove(bottomNavItem.graph)
                                tabStack.add(bottomNavItem.graph)
                            }
                            nav(navHostController = navHostController, destination = bottomNavItem.graph.route)
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navHostController,
            startDestination = NestedGraph.BottomNavYoutube1.route,
            Modifier.padding(innerPadding)
        ) {
            bottomNavLikeYoutubeGraph1(
                rootNavController = rootNavController,
                navHostController = navHostController,
                backClick = { backClick(rootNavController, navHostController, tabStack, it) }
            )
            bottomNavLikeYoutubeGraph2(
                rootNavController = rootNavController,
                navHostController = navHostController,
                backClick = { backClick(rootNavController, navHostController, tabStack, it) }
            )
            bottomNavLikeYoutubeGraph3(
                rootNavController = rootNavController,
                navHostController = navHostController,
                backClick = { backClick(rootNavController, navHostController, tabStack, it) }
            )
            bottomNavLikeYoutubeGraph4(
                rootNavController = rootNavController,
                navHostController = navHostController,
                backClick = { backClick(rootNavController, navHostController, tabStack, it) }
            )
        }
    }
}

private fun backClick(
    rootNavController: NavController,
    navHostController: NavController,
    tabStack: SnapshotStateList<NestedGraph>,
    graph: NestedGraph
) {
    when (val size = tabStack.size) {
        0 -> rootNavController.popBackStack()
        1 -> if (graph == NestedGraph.BottomNavYoutube1) {
            rootNavController.popBackStack()
        } else {
            navHostController.navigate(NestedGraph.BottomNavYoutube1.route)
        }
        else -> {
            tabStack.removeAt(size - 1)
            nav(navHostController = navHostController, destination = tabStack[size - 2].route)
        }
    }
}

private fun nav(navHostController: NavController, destination: String){
    navHostController.navigate(destination) {
        popUpTo(navHostController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
