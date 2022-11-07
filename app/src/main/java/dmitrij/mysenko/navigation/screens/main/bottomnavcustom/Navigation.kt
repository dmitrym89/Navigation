package dmitrij.mysenko.navigation.screens.main.bottomnavcustom

import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen

fun NavGraphBuilder.bottomNavCustomGraph1(
    rootNavController: NavController,
    navHostController: NavController,
    backClick: (NestedGraph) -> Unit,
    contentBottomPadding: Dp
) {
    navigation(
        startDestination = Screen.BottomNavCustomScreen11.makeRoute(NestedGraph.BottomNavCustom1),
        route = NestedGraph.BottomNavCustom1.route
    ) {
        composable(route = Screen.BottomNavCustomScreen11.makeRoute(NestedGraph.BottomNavCustom1)) {
            BottomNavCustomScreen11(rootNavController = rootNavController, navHostController = navHostController, backClick = backClick, contentBottomPadding = contentBottomPadding)
        }
        composable(route = Screen.BottomNavCustomScreen12.makeRoute(NestedGraph.BottomNavCustom1)){
            BottomNavCustomScreen12(rootNavController = rootNavController, navHostController = navHostController)
        }
    }
}

fun NavGraphBuilder.bottomNavCustomGraph2(
    rootNavController: NavController,
    navHostController: NavController,
    backClick: (NestedGraph) -> Unit
) {
    navigation(startDestination = Screen.BottomNavCustomScreen21.makeRoute(NestedGraph.BottomNavCustom2), route = NestedGraph.BottomNavCustom2.route) {
        composable(route = Screen.BottomNavCustomScreen21.makeRoute(NestedGraph.BottomNavCustom2)) {
            BottomNavCustomScreen21(rootNavController = rootNavController, navHostController = navHostController, backClick = backClick)
        }
        composable(route = Screen.BottomNavCustomScreen22.makeRoute(NestedGraph.BottomNavCustom2)){
            BottomNavCustomScreen22(rootNavController = rootNavController, navHostController = navHostController)
        }
    }
}

fun NavGraphBuilder.bottomNavCustomGraph3(
    rootNavController: NavController,
    navHostController: NavController,
    backClick: (NestedGraph) -> Unit
) {
    navigation(startDestination = Screen.BottomNavCustomScreen31.makeRoute(NestedGraph.BottomNavCustom3), route = NestedGraph.BottomNavCustom3.route) {
        composable(route = Screen.BottomNavCustomScreen31.makeRoute(NestedGraph.BottomNavCustom3)) {
            BottomNavCustomScreen31(rootNavController = rootNavController, navHostController = navHostController, backClick = backClick)
        }
        composable(route = Screen.BottomNavCustomScreen32.makeRoute(NestedGraph.BottomNavCustom3)){
            BottomNavCustomScreen32(rootNavController = rootNavController, navHostController = navHostController)
        }
    }
}