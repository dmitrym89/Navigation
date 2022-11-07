package dmitrij.mysenko.navigation.screens.sign_in

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.MainActivity
import dmitrij.mysenko.navigation.navigation.LOGIN
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.shared.CurrentRoute
import dmitrij.mysenko.navigation.shared.readValue

@Composable
fun SignInScreen1(navController: NavController) {
    val intent = (LocalContext.current as? MainActivity)?.intent
    val uri: Uri? = intent?.data
    if (uri != null) {
        navController.navigate(uri)
    } else {
        if (LocalContext.current.readValue(key = LOGIN)?.isNotBlank() == true) {
            navController.navigate(NestedGraph.Main.route) {
                popUpTo(NestedGraph.SignIn.route) { inclusive = true }
            }
        }
    }

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
            label = { Text(text = "Login for sign in") },
            onValueChange = {
                login = it
            },
            modifier = Modifier.fillMaxWidth(0.9f)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (login.isNotBlank()) {
                    navController.navigate(Screen.SignInScreen2.withArgs(login))
                }
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Next step")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "go to sign up", modifier = Modifier.clickable {
            navController.navigate(NestedGraph.SignUp.route)
        })
    }
}


