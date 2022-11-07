package dmitrij.mysenko.navigation.screens.main.bottomnavgoogle

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.shared.CurrentRoute
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomNavScreen12(rootNavController: NavController, navHostController: NavController, bottomSheetState: ModalBottomSheetState){
    val scope = rememberCoroutineScope()
    BackHandler(enabled = bottomSheetState.isVisible) {
        scope.launch{
            bottomSheetState.hide()
        }
    }
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
                scope.launch {
                    bottomSheetState.show()
                }
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Show bottom sheet under navbar")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                rootNavController.navigate(Screen.AfterMainScreen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Next screen in root graph")
        }
    }
}