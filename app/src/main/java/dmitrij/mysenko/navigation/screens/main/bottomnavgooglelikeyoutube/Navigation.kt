package dmitrij.mysenko.navigation.screens.main.bottomnavgooglelikeyoutube

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.screens.main.bottomnavgooglelikeyoutube.BottomNavYoutubeScreen11

fun NavGraphBuilder.bottomNavLikeYoutubeGraph1(
    rootNavController: NavController,
    navHostController: NavController,
    backClick: (NestedGraph) -> Unit
) {
    navigation(
        startDestination = Screen.BottomNavYoutubeScreen11.makeRoute(NestedGraph.BottomNavYoutube1),
        route = NestedGraph.BottomNavYoutube1.route
    ) {
        composable(route = Screen.BottomNavYoutubeScreen11.makeRoute(NestedGraph.BottomNavYoutube1)) {
            BottomNavYoutubeScreen11(rootNavController = rootNavController, navHostController = navHostController, backClick = backClick)
        }
        composable(route = Screen.BottomNavYoutubeScreen12.makeRoute(NestedGraph.BottomNavYoutube1)){
            BottomNavYoutubeScreen12(rootNavController = rootNavController, navHostController = navHostController)
        }
    }
}

fun NavGraphBuilder.bottomNavLikeYoutubeGraph2(
    rootNavController: NavController,
    navHostController: NavController,
    backClick: (NestedGraph) -> Unit
) {
    navigation(startDestination = Screen.BottomNavYoutubeScreen21.makeRoute(NestedGraph.BottomNavYoutube2), route = NestedGraph.BottomNavYoutube2.route) {
        composable(route = Screen.BottomNavYoutubeScreen21.makeRoute(NestedGraph.BottomNavYoutube2)) {
            BottomNavYoutubeScreen21(rootNavController = rootNavController, navHostController = navHostController, backClick = backClick)
        }
        composable(route = Screen.BottomNavYoutubeScreen22.makeRoute(NestedGraph.BottomNavYoutube2)){
            BottomNavYoutubeScreen22(rootNavController = rootNavController, navHostController = navHostController)
        }
    }
}

fun NavGraphBuilder.bottomNavLikeYoutubeGraph3(
    rootNavController: NavController,
    navHostController: NavController,
    backClick: (NestedGraph) -> Unit
) {
    navigation(startDestination = Screen.BottomNavYoutubeScreen31.makeRoute(NestedGraph.BottomNavYoutube3), route = NestedGraph.BottomNavYoutube3.route) {
        composable(route = Screen.BottomNavYoutubeScreen31.makeRoute(NestedGraph.BottomNavYoutube3)) {
            BottomNavYoutubeScreen31(rootNavController = rootNavController, navHostController = navHostController, backClick = backClick)
        }
        composable(route = Screen.BottomNavYoutubeScreen32.makeRoute(NestedGraph.BottomNavYoutube3)){
            BottomNavYoutubeScreen32(rootNavController = rootNavController, navHostController = navHostController)
        }
    }
}


fun NavGraphBuilder.bottomNavLikeYoutubeGraph4(
    rootNavController: NavController,
    navHostController: NavController,
    backClick: (NestedGraph) -> Unit
) {
    navigation(startDestination = Screen.BottomNavYoutubeScreen41.makeRoute(NestedGraph.BottomNavYoutube4), route = NestedGraph.BottomNavYoutube4.route) {
        composable(route = Screen.BottomNavYoutubeScreen41.makeRoute(NestedGraph.BottomNavYoutube4)) {
            BottomNavYoutubeScreen41(rootNavController = rootNavController, navHostController = navHostController, backClick = backClick)
        }
        composable(route = Screen.BottomNavYoutubeScreen42.makeRoute(NestedGraph.BottomNavYoutube4)){
            BottomNavYoutubeScreen42(rootNavController = rootNavController, navHostController = navHostController)
        }
    }
}


