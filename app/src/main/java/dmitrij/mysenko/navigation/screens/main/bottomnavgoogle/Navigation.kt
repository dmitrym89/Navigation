package dmitrij.mysenko.navigation.screens.main.bottomnavgoogle

import androidx.compose.material.BottomSheetState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen

@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.bottomNavGoogleGraph1(
    rootNavController: NavController,
    navHostController: NavController,
    bottomSheetState: ModalBottomSheetState
) {
    navigation(startDestination = Screen.BottomNavScreen11.makeRoute(NestedGraph.BottomNav1), route = NestedGraph.BottomNav1.route) {
        composable(route = Screen.BottomNavScreen11.makeRoute(NestedGraph.BottomNav1)) {
            BottomNavScreen11(rootNavController = rootNavController, navHostController = navHostController)
        }
        composable(route = Screen.BottomNavScreen12.makeRoute(NestedGraph.BottomNav1)){
            BottomNavScreen12(rootNavController = rootNavController, navHostController = navHostController, bottomSheetState = bottomSheetState)
        }
    }
}

fun NavGraphBuilder.bottomNavGoogleGraph2(rootNavController: NavController, navHostController: NavController) {
    navigation(startDestination = Screen.BottomNavScreen21.makeRoute(NestedGraph.BottomNav2), route = NestedGraph.BottomNav2.route) {
        composable(route = Screen.BottomNavScreen21.makeRoute(NestedGraph.BottomNav2)) {
            BottomNavScreen21(rootNavController = rootNavController, navHostController = navHostController)
        }
        composable(
            route = Screen.BottomNavScreen22.makeRoute(NestedGraph.BottomNav2),
            deepLinks = listOf(navDeepLink { uriPattern = "https://www.examplenav.com/bns22"})
        ){
            BottomNavScreen22(rootNavController = rootNavController, navHostController = navHostController)
        }
    }
}

fun NavGraphBuilder.bottomNavGoogleGraph3(rootNavController: NavController, navHostController: NavController) {
    navigation(startDestination = Screen.BottomNavScreen31.makeRoute(NestedGraph.BottomNav3), route = NestedGraph.BottomNav3.route) {
        composable(route = Screen.BottomNavScreen31.makeRoute(NestedGraph.BottomNav3)) {
            BottomNavScreen31(rootNavController = rootNavController, navHostController = navHostController)
        }
        composable(route = Screen.BottomNavScreen32.makeRoute(NestedGraph.BottomNav3)){
            BottomNavScreen32(rootNavController = rootNavController, navHostController = navHostController)
        }
    }
}