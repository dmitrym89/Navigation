package dmitrij.mysenko.navigation.shared

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun CurrentRoute(navController: NavController, prefix: String = "Current route: "){
    Text(text = prefix + (navController.currentDestination?.route ?: "unknown"))
}