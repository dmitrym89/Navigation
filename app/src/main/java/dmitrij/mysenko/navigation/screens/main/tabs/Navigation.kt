package dmitrij.mysenko.navigation.screens.main.tabs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.screens.main.sheets.HalfModalBottomSheetScreen
import dmitrij.mysenko.navigation.screens.main.sheets.ModalBottomSheetScreen
import dmitrij.mysenko.navigation.screens.main.sheets.ScaffoldBottomSheetScreen
import dmitrij.mysenko.navigation.screens.main.sheets.SheetScreen

fun NavGraphBuilder.tabsGraph(navController: NavController) {
    navigation(
        startDestination = Screen.TabsScreen.route,
        route = NestedGraph.Tabs.route
    ) {
        composable(route = Screen.TabsScreen.route){
            TabsScreen(navController = navController)
        }
        composable(route = Screen.DefaultTabsScreen.route) {
            DefaultTabsScreen(navController = navController)
        }
        composable(route = Screen.SwipeableTabsScreen.route) {
            SwipeableTabsScreen(navController = navController)
        }
        composable(route = Screen.CustomTabsScreen.route) {
            CustomTabsScreen(navController = navController)
        }
    }
}