package dmitrij.mysenko.navigation.screens.main.bottomnavgooglelikeyoutube

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.shared.CurrentRoute

@Composable
fun BottomNavYoutubeScreen31(
    rootNavController: NavController, navHostController: NavController,
    backClick: (NestedGraph) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {


        BackHandler {
            backClick.invoke(NestedGraph.BottomNavYoutube3)
        }

        CurrentRoute(navController = rootNavController, prefix = "Root current route: ")
        Spacer(modifier = Modifier.height(20.dp))
        CurrentRoute(navController = navHostController)
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navHostController.navigate(Screen.BottomNavYoutubeScreen32.makeRoute(NestedGraph.BottomNavYoutube3))
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Next screen in this tab")
        }
    }
}