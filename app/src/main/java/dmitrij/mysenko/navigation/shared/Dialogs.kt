package dmitrij.mysenko.navigation.shared

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties


@Composable
fun SimpleDialog(
    state: MutableState<Boolean>,
    header: String = "Alarm!!",
    text: String = "Typical warning. Сan be closed by pressing ok, back or by clicking outside",
    buttonText: String = "OK"
) {
    if (state.value) {
        Dialog(
            onDismissRequest = { state.value = false }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(color = Color.White, RoundedCornerShape(10.dp))
                    .padding(all = 20.dp)
            ) {
                Text(text = header, fontSize = 20.sp, fontWeight = FontWeight.W700)
                Text(text = text)
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = { state.value = false }) {
                    Text(buttonText)
                }
            }
        }
    }
}

@Composable
fun QuestionDialog(
    state: MutableState<Boolean>,
    positiveAnswer: () -> Unit,
    negativeAnswer: () -> Unit,
    header: String = "Alarm!!",
    text: String = "Question dialog. Can only be closed by pressing YES or NO",
    positiveButtonText: String = "YES",
    negativeButtonText: String = "NO",
) {
    if (state.value) {
        Dialog(
            onDismissRequest = { state.value = false },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(color = Color.White, RoundedCornerShape(10.dp))
                    .padding(all = 20.dp)
            ) {
                Text(text = header, fontSize = 20.sp, fontWeight = FontWeight.W700)
                Text(text = text)
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Button(
                        onClick = {
                            positiveAnswer.invoke()
                            state.value = false
                        }
                    ) {
                        Text(positiveButtonText)
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Button(
                        onClick = {
                            negativeAnswer.invoke()
                            state.value = false
                        }
                    ) {
                        Text(negativeButtonText)
                    }
                }
            }
        }
    }
}

@Composable
fun SingleChoiceDialog(
    state: MutableState<Boolean>,
    result: (String) -> Unit,
    header: String = "Single choice dialog",
    items: List<String> = listOf("First", "Second", "Third")
) {
    if (state.value) {
        Dialog(
            onDismissRequest = { state.value = false },
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(color = Color.White, RoundedCornerShape(10.dp))
                    .padding(all = 20.dp)
            ) {
                Text(text = header, fontSize = 20.sp, fontWeight = FontWeight.W700)
                Spacer(modifier = Modifier.height(20.dp))
                items.forEach { item ->
                    Text(
                        item,
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .fillMaxWidth()
                            .background(color = Color.LightGray)
                            .clickable {
                                result.invoke(item)
                                state.value = false
                            }
                            .padding(10.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MultipleChoiceDialog(
    state: MutableState<Boolean>,
    result: (List<String>) -> Unit,
    header: String = "Multiple choice dialog",
    items: List<String> = listOf("First", "Second", "Third"),
    positiveButtonText: String = "Choose",
    negativeButtonText: String = "Dismiss",
) {
    val choose = items.map { Pair(it, false) }.toMutableStateMap()

    if (state.value) {
        Dialog(
            onDismissRequest = { state.value = false },
            properties = DialogProperties(
                dismissOnClickOutside = false,
                dismissOnBackPress = false
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(color = Color.White, RoundedCornerShape(10.dp))
                    .padding(all = 20.dp)
            ) {
                Text(text = header, fontSize = 20.sp, fontWeight = FontWeight.W700)
                Spacer(modifier = Modifier.height(20.dp))
                choose.forEach {  item ->
                    Text(
                        item.key,
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                            .fillMaxWidth()
                            .background(color = if (item.value) Color.DarkGray else Color.LightGray)
                            .clickable {
                                choose[item.key] = !item.value
                            }
                            .padding(10.dp)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Button(
                        onClick = {
                            result.invoke(choose.filterValues { it }.keys.toList())
                            state.value = false
                        }
                    ) {
                        Text(positiveButtonText)
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Button(
                        onClick = {
                            state.value = false
                        }
                    ) {
                        Text(negativeButtonText)
                    }
                }
            }
        }
    }
}