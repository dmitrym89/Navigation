package dmitrij.mysenko.navigation.screens.main.bottomnav


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.shared.*

@Composable
fun BottomNavScreen(navController: NavController) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        CurrentRoute(navController = navController)
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.BottomNavGoogleScreen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(
                text = "BottomNavigation(Google)"
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.BottomNavYoutubeScreen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(
                text = "BottomNavigation(Google) like YouTube"
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.BottomNavCustomScreen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(
                text = "BottomNavigation(Custom)"
            )
        }
    }
}



