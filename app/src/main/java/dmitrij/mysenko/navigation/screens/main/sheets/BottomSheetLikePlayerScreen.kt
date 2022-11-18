package dmitrij.mysenko.navigation.screens.main.sheets

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.shared.BottomSheetContent
import dmitrij.mysenko.navigation.shared.CurrentRoute
import kotlinx.coroutines.launch
import java.math.RoundingMode
import kotlin.reflect.KFunction


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetLikePlayerScreen(navController: NavController) {

    val bottomSheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState
    )
    val scope = rememberCoroutineScope()

    val bottomInset = with(
        LocalDensity.current
    ) {
        WindowInsets.navigationBars.getBottom(this)/this.density
    }

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 50.dp + bottomInset.dp,
        sheetContent = { BottomSheetPlayerContent() }
    ) {
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
                        if (bottomSheetState.isCollapsed) {
                            bottomSheetState.expand()
                        } else if (bottomSheetState.isExpanded) {
                            bottomSheetState.collapse()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(
                    text = "Show/Hide"
                )
            }
        }
    }
}

@Composable
fun BottomSheetPlayerContent(){

}

@Composable
fun PlayerButtons(){
    
}
