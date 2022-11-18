package dmitrij.mysenko.navigation.screens.main.drawer

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.shared.CurrentRoute

@Composable
fun DrawerTopScreen(rootNavController: NavController, navHostController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        CurrentRoute(navController = rootNavController, prefix = "Root current route: ")
        Spacer(modifier = Modifier.height(20.dp))
        CurrentRoute(navController = navHostController)
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navHostController.navigate(Screen.DrawerUserScreen.makeRoute(NestedGraph.DrawerTop))
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "To user screen")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navHostController.navigate(Screen.DrawerImageScreen.makeRoute(NestedGraph.DrawerTop))
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "To image screen")
        }

    }
}