package dmitrij.mysenko.navigation.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.shared.CurrentRoute
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        CurrentRoute(navController = navController)

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.BottomNavScreen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(
                text = "BottomNavigation"
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.DialogScreen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(
                text = "Dialogs"
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.PopupScreen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(
                text = "Popups"
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(NestedGraph.Sheets.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(
                text = "Sheets"
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(NestedGraph.Tabs.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(
                text = "Tabs"
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.DrawerScreen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(
                text = "Drawer"
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(NestedGraph.Other.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(
                text = "Other"
            )
        }
    }
}


