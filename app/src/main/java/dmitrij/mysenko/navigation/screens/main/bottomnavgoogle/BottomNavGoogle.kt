package dmitrij.mysenko.navigation.screens.main.bottomnavgoogle

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dmitrij.mysenko.navigation.R
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.shared.BottomSheetContentMoreThenHalfScreen

sealed class BottomNavItem(
    val screen: Screen,
    val icon: ImageVector,
    @StringRes val resourceId: Int,
    val graph: NestedGraph
) {
    object BottomNavItem1 : BottomNavItem(
        Screen.BottomNavScreen11,
        Icons.Filled.Favorite,
        R.string.bottom_nav_item_1,
        NestedGraph.BottomNav1
    )

    object BottomNavItem2 : BottomNavItem(
        Screen.BottomNavScreen21,
        Icons.Filled.AccountBox,
        R.string.bottom_nav_item_2,
        NestedGraph.BottomNav2
    )

    object BottomNavItem3 : BottomNavItem(
        Screen.BottomNavScreen31,
        Icons.Filled.Call,
        R.string.bottom_nav_item_3,
        NestedGraph.BottomNav3
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomNavGoogleScreen(rootNavController: NavController) {

    val navHostController = rememberNavController()

    val items = listOf(
        BottomNavItem.BottomNavItem1,
        BottomNavItem.BottomNavItem2,
        BottomNavItem.BottomNavItem3
    )

    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

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

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        scrimColor = Color.Unspecified,
        sheetContent = {
            BottomSheetContentMoreThenHalfScreen()
        }) {
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
                                navHostController.navigate(bottomNavItem.graph.route) {
                                    popUpTo(navHostController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navHostController,
                startDestination = NestedGraph.BottomNav1.route,
                Modifier.padding(innerPadding)
            ) {
                bottomNavGoogleGraph1(
                    rootNavController = rootNavController,
                    navHostController = navHostController,
                    bottomSheetState = modalBottomSheetState
                )
                bottomNavGoogleGraph2(
                    rootNavController = rootNavController,
                    navHostController = navHostController
                )
                bottomNavGoogleGraph3(
                    rootNavController = rootNavController,
                    navHostController = navHostController
                )
            }
        }
    }
}