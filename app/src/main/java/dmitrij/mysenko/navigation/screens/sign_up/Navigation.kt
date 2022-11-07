package dmitrij.mysenko.navigation.screens.sign_up

import androidx.navigation.*
import androidx.navigation.compose.composable
import dmitrij.mysenko.navigation.navigation.LOGIN
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.navigation.PASSWORD
import dmitrij.mysenko.navigation.screens.Screen

fun NavGraphBuilder.signUpGraph(navController: NavController) {
    navigation(startDestination = Screen.SignUpScreen1.makeRoute(NestedGraph.SignIn), route = NestedGraph.SignUp.route) {
        composable(
            route = Screen.SignUpScreen1.makeRoute(NestedGraph.SignIn),
            deepLinks = listOf(navDeepLink { uriPattern = "https://www.examplenav.com/signup" })
        ) {
            SignUpScreen1(navController = navController)
        }
        composable(
            route = Screen.SignUpScreen2.makeRoute(NestedGraph.SignIn) + "/{$LOGIN}",
            arguments = listOf(
                navArgument(LOGIN) {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { entry ->
            SignUpScreen2(navController = navController, login = entry.arguments?.getString(LOGIN) ?: "default_login")
        }
        composable(
            route = Screen.SignUpScreen3.makeRoute(NestedGraph.SignIn) + "/{$LOGIN}/{$PASSWORD}",
            arguments = listOf(
                navArgument(LOGIN) {
                    type = NavType.StringType
                    nullable = false
                },
                navArgument(PASSWORD) {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { entry ->
            SignUpScreen3(
                navController = navController,
                login = entry.arguments?.getString(LOGIN) ?: "default_login",
                password = entry.arguments?.getString(PASSWORD) ?: "default_password"
            )
        }
    }
}