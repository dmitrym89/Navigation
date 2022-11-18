package dmitrij.mysenko.navigation.screens.main.drawer

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen


fun NavGraphBuilder.drawerTopGraph(rootNavController: NavController, navHostController: NavController) {
    navigation(startDestination = Screen.DrawerTopScreen.makeRoute(NestedGraph.DrawerTop), route = NestedGraph.DrawerTop.route) {
        composable(route = Screen.DrawerTopScreen.makeRoute(NestedGraph.DrawerTop)) {
            DrawerTopScreen(rootNavController = rootNavController, navHostController = navHostController)
        }
        composable(route = Screen.DrawerImageScreen.makeRoute(NestedGraph.DrawerTop)){
            DrawerImageScreen(rootNavController = rootNavController, navHostController = navHostController, graph = NestedGraph.DrawerTop)
        }
        composable(route = Screen.DrawerUserScreen.makeRoute(NestedGraph.DrawerTop)){
            DrawerUserScreen(rootNavController = rootNavController, navHostController = navHostController, graph = NestedGraph.DrawerTop)
        }
    }
}

fun NavGraphBuilder.drawerLatestGraph(rootNavController: NavController, navHostController: NavController) {
    navigation(startDestination = Screen.DrawerLatestScreen.makeRoute(NestedGraph.DrawerLatest), route = NestedGraph.DrawerLatest.route) {
        composable(route = Screen.DrawerLatestScreen.makeRoute(NestedGraph.DrawerLatest)) {
            DrawerLatestScreen(rootNavController = rootNavController, navHostController = navHostController)
        }
        composable(route = Screen.DrawerImageScreen.makeRoute(NestedGraph.DrawerLatest)){
            DrawerImageScreen(rootNavController = rootNavController, navHostController = navHostController, graph = NestedGraph.DrawerLatest)
        }
        composable(route = Screen.DrawerUserScreen.makeRoute(NestedGraph.DrawerLatest)){
            DrawerUserScreen(rootNavController = rootNavController, navHostController = navHostController, graph = NestedGraph.DrawerLatest)
        }
    }
}