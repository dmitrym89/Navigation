package dmitrij.mysenko.navigation.screens.main.sheets

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen

fun NavGraphBuilder.sheetsGraph(navController: NavController) {
    navigation(
        startDestination = Screen.SheetScreen.route,
        route = NestedGraph.Sheets.route
    ) {
        composable(route = Screen.SheetScreen.route){
            SheetScreen(navController = navController)
        }
        composable(route = Screen.ModalBottomSheetScreen.route) {
            ModalBottomSheetScreen(navController = navController)
        }
        composable(route = Screen.HalfModalBottomSheetScreen.route) {
            HalfModalBottomSheetScreen(navController = navController)
        }
        composable(route = Screen.ScaffoldBottomSheetScreen.route) {
            ScaffoldBottomSheetScreen(navController = navController)
        }
        composable(route = Screen.BottomSheetLikePlayerScreen.route) {
            BottomSheetLikePlayerScreen(navController = navController)
        }
    }
}