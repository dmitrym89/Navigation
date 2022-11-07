package dmitrij.mysenko.navigation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.main.mainGraph
import dmitrij.mysenko.navigation.screens.sign_in.signInGraph

@Composable
fun Navigation() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NestedGraph.SignIn.route) {
        signInGraph(navController = navController)
        mainGraph(navController = navController)
    }
}





