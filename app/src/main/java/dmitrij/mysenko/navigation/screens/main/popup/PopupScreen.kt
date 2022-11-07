package dmitrij.mysenko.navigation.screens.main.popup


import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.shared.*

@Composable
fun PopupScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        val simplePopupState = remember { mutableStateOf(false) }
        val positionProviderPopupState = remember { mutableStateOf(false) }

        Spacer(modifier = Modifier.height(20.dp))
        CurrentRoute(navController = navController)
        Spacer(modifier = Modifier.height(20.dp))

        val simpleState = remember { mutableStateOf(Alignment.Center) }
        TableAlignment(state = simpleState)

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                simplePopupState.value = true
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Simple popup")
        }

        Spacer(modifier = Modifier.height(20.dp))
        val positionProviderState = remember { mutableStateOf(MyPopupAlign.UnderStart) }
        MyTableAlignment(state = positionProviderState)

        Spacer(modifier = Modifier.height(20.dp))
        Box {
            PositionProviderPopup(
                state = positionProviderPopupState,
                align = positionProviderState.value
            )
            Button(
                onClick = {
                    positionProviderPopupState.value = true
                },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = "Popup with position provider")
            }
        }

        Spacer(modifier = Modifier.height(500.dp))
        Text("bottom here")
        Spacer(modifier = Modifier.height(20.dp))

        SimplePopup(state = simplePopupState, alignment = simpleState.value)
    }
}

@Composable
private fun TableAlignment(state: MutableState<Alignment>) {
    Row {
        TableAlignmentCell(state = state, alignment = Alignment.TopStart)
        TableAlignmentCell(state = state, alignment = Alignment.TopCenter)
        TableAlignmentCell(state = state, alignment = Alignment.TopEnd)
    }
    Row {
        TableAlignmentCell(state = state, alignment = Alignment.CenterStart)
        TableAlignmentCell(state = state, alignment = Alignment.Center)
        TableAlignmentCell(state = state, alignment = Alignment.CenterEnd)
    }
    Row {
        TableAlignmentCell(state = state, alignment = Alignment.BottomStart)
        TableAlignmentCell(state = state, alignment = Alignment.BottomCenter)
        TableAlignmentCell(state = state, alignment = Alignment.BottomEnd)
    }
}

@Composable
private fun RowScope.TableAlignmentCell(
    alignment: Alignment,
    state: MutableState<Alignment>,
    modifier: Modifier = Modifier
) {
    val name = remember {
        getDirectionName(alignment)
    }
    Text(
        text = name,
        textAlign = TextAlign.Center,
        modifier = modifier
            .weight(1f)
            .fillMaxWidth()
            .height(100.dp)
            .border(
                BorderStroke(2.dp, Color.Black)
            )
            .background(color = if (state.value == alignment) Color.Green else Color.White)
            .clickable { state.value = alignment }
    )
}

private fun getDirectionName(alignment: Alignment) = buildString {
    when ((alignment as BiasAlignment).verticalBias) {
        -1f -> append("Top")
        0f -> append("Center")
        1f -> append("Bottom")
    }
    when (alignment.horizontalBias) {
        -1f -> append("Start")
        0f -> if (alignment.verticalBias != 0f) append("Center")
        1f -> append("End")
    }
}

@Composable
private fun MyTableAlignment(state: MutableState<MyPopupAlign>) {
    Row {
        MyTableAlignmentCell(state = state, alignment = MyPopupAlign.UnderStart)
        MyTableAlignmentCell(state = state, alignment = MyPopupAlign.UnderEnd)
    }
    Row {
        MyTableAlignmentCell(state = state, alignment = MyPopupAlign.BeforeStart)
        MyTableAlignmentCell(state = state, alignment = MyPopupAlign.BeforeEnd)
    }
}

@Composable
private fun RowScope.MyTableAlignmentCell(
    alignment: MyPopupAlign,
    state: MutableState<MyPopupAlign>,
    modifier: Modifier = Modifier
) {
    Text(
        text = alignment.name(),
        textAlign = TextAlign.Center,
        modifier = modifier
            .weight(1f)
            .fillMaxWidth()
            .height(100.dp)
            .border(
                BorderStroke(2.dp, Color.Black)
            )
            .background(color = if (state.value == alignment) Color.Green else Color.White)
            .clickable { state.value = alignment }
    )
}
