package dmitrij.mysenko.navigation.screens.main.bottomnavgoogle

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.navigation.LOGIN
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.shared.CurrentRoute
import dmitrij.mysenko.navigation.shared.readValue
import dmitrij.mysenko.navigation.shared.writeValue

@Composable
fun BottomNavScreen22(rootNavController: NavController, navHostController: NavController){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        CurrentRoute(navController = rootNavController, prefix = "Root current route: ")
        Spacer(modifier = Modifier.height(20.dp))
        CurrentRoute(navController = navHostController)
        Spacer(modifier = Modifier.height(20.dp))
        val context = LocalContext.current
        Button(
            onClick = {
                context.writeValue(LOGIN, "")
                rootNavController.navigate(NestedGraph.SignIn.route){
                    popUpTo(NestedGraph.Main.route){inclusive = true}
                }
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Logout(${context.readValue(LOGIN)})")
        }
    }
}