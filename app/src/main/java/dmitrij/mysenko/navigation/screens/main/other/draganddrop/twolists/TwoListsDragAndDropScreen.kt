package dmitrij.mysenko.navigation.screens.main.other.draganddrop.twolists

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.shared.CurrentRoute

@Composable
fun TwoListsDragAndDropScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        CurrentRoute(navController = navController)
        Spacer(modifier = Modifier.height(20.dp))


    }
}





