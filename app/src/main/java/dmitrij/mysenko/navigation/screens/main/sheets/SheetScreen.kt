package dmitrij.mysenko.navigation.screens.main.sheets


import android.content.Intent
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.MainActivity
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.shared.*

@Composable
fun SheetScreen(navController: NavController) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        CurrentRoute(navController = navController)
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.ModalBottomSheetScreen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "ModalBottomSheet")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.HalfModalBottomSheetScreen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "HalfModalBottomSheet")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.ScaffoldBottomSheetScreen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "ScaffoldBottomSheet")
        }

//        val context = LocalContext.current
//        val deepLinkIntent = Intent(
//            Intent.ACTION_VIEW,
//            "https://www.examplenav.com/signup".toUri(),
//            context,
//            MainActivity::class.java
//        )
//        Spacer(modifier = Modifier.height(20.dp))
//        Button(
//            onClick = {
//                context.startActivity(deepLinkIntent)
//            },
//            modifier = Modifier.fillMaxWidth(0.8f)
//        ) {
//            Text(text = "Deeplink")
//        }
    }
}



