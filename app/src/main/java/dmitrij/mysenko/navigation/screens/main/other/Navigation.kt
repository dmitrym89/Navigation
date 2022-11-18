package dmitrij.mysenko.navigation.screens.main.other

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen

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
    }
}