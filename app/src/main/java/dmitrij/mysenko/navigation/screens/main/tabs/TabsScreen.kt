package dmitrij.mysenko.navigation.screens.main.tabs

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.shared.CurrentRoute

@Composable
fun TabsScreen(navController: NavController){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        CurrentRoute(navController = navController)
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.DefaultTabsScreen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "DefaultTabsScreen")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.SwipeableTabsScreen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "SwipeableTabsScreen")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.CustomTabsScreen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "CustomTabsScreen")
        }
    }
}