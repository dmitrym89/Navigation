package dmitrij.mysenko.navigation.screens.main.lookahead

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen

fun NavGraphBuilder.lookaheadGraph(navController: NavController) {
    navigation(
        startDestination = Screen.LookaheadLayoutScreen.route,
        route = NestedGraph.Lookahead.route
    ) {
        composable(route = Screen.LookaheadLayoutScreen.route){
            LookaheadLayoutScreen(navController = navController)
        }
        composable(route = Screen.ColumnToRowScreen.route){
            ColumnToRowScreen(navController = navController)
        }
    }
}