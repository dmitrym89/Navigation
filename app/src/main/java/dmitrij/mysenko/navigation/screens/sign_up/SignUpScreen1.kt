package dmitrij.mysenko.navigation.screens.sign_up

import androidx.compose.foundation.clickable
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
fun SignUpScreen1(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        var login by remember {
            mutableStateOf("")
        }

        CurrentRoute(navController = navController)

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = login,
            label = {
                Text("Login for sign up")
            },
            onValueChange = {
                login = it
            },
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (login.isNotBlank()) {
                    navController.navigate(Screen.SignUpScreen2.withArgs(NestedGraph.SignIn, login))
                }
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Next step")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "go to sign in", modifier = Modifier.clickable {
            navController.popBackStack()
        })
    }
}


