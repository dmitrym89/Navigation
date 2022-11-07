package dmitrij.mysenko.navigation.screens.main.sheets

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.shared.BottomSheetContent
import dmitrij.mysenko.navigation.shared.BottomSheetContentMoreThenHalfScreen
import dmitrij.mysenko.navigation.shared.CurrentRoute
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HalfModalBottomSheetScreen(navController: NavController) {

    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.HalfExpanded
    )
    val scope = rememberCoroutineScope()

    BackHandler(enabled = modalBottomSheetState.isVisible) {
        scope.launch{
            modalBottomSheetState.hide()
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = {
            BottomSheetContentMoreThenHalfScreen()
        }) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            CurrentRoute(navController = navController)
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    scope.launch {
                        modalBottomSheetState.show()
                    }
                },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = "Show")
            }
        }
    }
}