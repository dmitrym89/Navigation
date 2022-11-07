package dmitrij.mysenko.navigation.screens.main.bottomnav

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.screens.main.bottomnavcustom.BottomNavCustomScreen
import dmitrij.mysenko.navigation.screens.main.bottomnavgoogle.BottomNavGoogleScreen
import dmitrij.mysenko.navigation.screens.main.bottomnavgooglelikeyoutube.BottomNavYoutubeScreen
import dmitrij.mysenko.navigation.screens.main.tabs.CustomTabsScreen
import dmitrij.mysenko.navigation.screens.main.tabs.DefaultTabsScreen
import dmitrij.mysenko.navigation.screens.main.tabs.SwipeableTabsScreen
import dmitrij.mysenko.navigation.screens.main.tabs.TabsScreen

fun NavGraphBuilder.bottomNavGraph(navController: NavController) {
    navigation(
        startDestination = Screen.BottomNavScreen.route,
        route = NestedGraph.BottomNav.route
    ) {
        composable(route = Screen.BottomNavScreen.route){
            BottomNavScreen(navController = navController)
        }
        composable(route = Screen.BottomNavYoutubeScreen.route){
            BottomNavYoutubeScreen(rootNavController = navController)
        }
        composable(route = Screen.BottomNavGoogleScreen.route){
            BottomNavGoogleScreen(rootNavController = navController)
        }
        composable(route = Screen.BottomNavCustomScreen.route){
            BottomNavCustomScreen(rootNavController = navController)
        }
    }
}
