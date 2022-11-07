package dmitrij.mysenko.navigation.screens.main.dialog


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.shared.*

@Composable
fun DialogScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        val simpleDialogState = remember { mutableStateOf(false) }
        val questionDialogState = remember { mutableStateOf(false) }
        val singleChoiceDialogState = remember { mutableStateOf(false) }
        val multipleChoiceDialogState = remember { mutableStateOf(false) }

        CurrentRoute(navController = navController)
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                simpleDialogState.value = true
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Simple dialog")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                questionDialogState.value = true
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Question dialog")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                singleChoiceDialogState.value = true
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Single choice dialog")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                multipleChoiceDialogState.value = true
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Multiple choice dialog")
        }

        SimpleDialog(state = simpleDialogState)
        QuestionDialog(state = questionDialogState, positiveAnswer = { }, negativeAnswer = {})
        SingleChoiceDialog(state = singleChoiceDialogState, result = { })
        MultipleChoiceDialog(state = multipleChoiceDialogState, result = { })

    }
}
