package dmitrij.mysenko.navigation.screens.sign_up

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.shared.CurrentRoute

@Composable
fun SignUpScreen2(navController: NavController, login: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        var password by remember {
            mutableStateOf("")
        }

        CurrentRoute(navController = navController)

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = password,
            label = {Text(text = "Password for $login")},
            onValueChange = {
                password = it
            },
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if(password.isNotBlank()){
                    navController.navigate(Screen.SignUpScreen3.withArgs(NestedGraph.SignIn, login, password))
                }
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Next step")
        }
    }
}


