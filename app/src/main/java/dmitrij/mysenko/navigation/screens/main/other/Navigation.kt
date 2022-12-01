package dmitrij.mysenko.navigation.screens.main.other

import androidx.navigation.*
import androidx.navigation.compose.composable
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.screens.main.other.grid.GridLayoutScreen

fun NavGraphBuilder.otherGraph(navController: NavController) {
    navigation(
        startDestination = Screen.OtherScreen.route,
        route = NestedGraph.Other.route
    ) {
        composable(route = Screen.OtherScreen.route){
            OtherScreen(navController = navController)
        }
        composable(route = Screen.WallpaperScreen.route){
            WallpaperScreen(navController = navController)
        }
        composable(route = Screen.GridLayoutScreen.route){
            GridLayoutScreen(navController = navController)
        }
    }
}