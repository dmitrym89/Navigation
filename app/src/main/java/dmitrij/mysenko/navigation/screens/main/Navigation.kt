package dmitrij.mysenko.navigation.screens.main

import androidx.navigation.*
import androidx.navigation.compose.composable
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.screens.aftermain.AfterMainScreen
import dmitrij.mysenko.navigation.screens.main.animation.animationTransitionGraph
import dmitrij.mysenko.navigation.screens.main.bottomnav.bottomNavGraph
import dmitrij.mysenko.navigation.screens.main.bottomnavcustom.BottomNavCustomScreen
import dmitrij.mysenko.navigation.screens.main.bottomnavgoogle.BottomNavGoogleScreen
import dmitrij.mysenko.navigation.screens.main.bottomnavgooglelikeyoutube.BottomNavYoutubeScreen
import dmitrij.mysenko.navigation.screens.main.dialog.DialogScreen
import dmitrij.mysenko.navigation.screens.main.popup.PopupScreen
import dmitrij.mysenko.navigation.screens.main.sheets.sheetsGraph
import dmitrij.mysenko.navigation.screens.main.tabs.tabsGraph

fun NavGraphBuilder.mainGraph(navController: NavController) {
    navigation(startDestination = Screen.MainScreen.route, route = NestedGraph.Main.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.AfterMainScreen.route,
            deepLinks = listOf(navDeepLink { uriPattern = "https://www.examplenav.com/ams" })
        ){
            AfterMainScreen(navController = navController)
        }
        composable(route = Screen.DialogScreen.route){
            DialogScreen(navController = navController)
        }
        composable(route = Screen.PopupScreen.route){
            PopupScreen(navController = navController)
        }
        sheetsGraph(navController = navController)
        tabsGraph(navController = navController)
        bottomNavGraph(navController = navController)
        animationTransitionGraph(navController = navController)
    }
}