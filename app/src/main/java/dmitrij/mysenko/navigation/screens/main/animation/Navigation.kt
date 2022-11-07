package dmitrij.mysenko.navigation.screens.main.animation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen

fun NavGraphBuilder.animationTransitionGraph(navController: NavController) {
    navigation(
        startDestination = Screen.AnimationTransitionScreen.route,
        route = NestedGraph.AnimationTransition.route
    ) {
        composable(route = Screen.AnimationTransitionScreen.route){
            AnimationTransitionScreen(navController = navController)
        }
        composable(
            route = Screen.AnimationTransition2Screen.route,

        ) {
            AnimationTransition2Screen(navController = navController)
        }
    }
}