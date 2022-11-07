package dmitrij.mysenko.navigation.screens.sign_in

import androidx.navigation.*
import androidx.navigation.compose.composable
import dmitrij.mysenko.navigation.navigation.LOGIN
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.screens.main.mainGraph
import dmitrij.mysenko.navigation.screens.sign_up.signUpGraph

fun NavGraphBuilder.signInGraph(navController: NavController) {
    navigation(startDestination = Screen.SignInScreen1.route, route = NestedGraph.SignIn.route) {
        signUpGraph(navController = navController)

        composable(route = Screen.SignInScreen1.route) {
            SignInScreen1(navController = navController)
        }
        composable(
            route = Screen.SignInScreen2.route + "/{$LOGIN}",
            arguments = listOf(
                navArgument(LOGIN) {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { entry ->
            SignInScreen2(navController = navController, login = entry.arguments?.getString(LOGIN) ?: "default_login")
        }
    }
}