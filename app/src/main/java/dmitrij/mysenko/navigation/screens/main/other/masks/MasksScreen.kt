package dmitrij.mysenko.navigation.screens.main.other.masks

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.shared.CurrentRoute

@Composable
fun MasksScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        CurrentRoute(navController = navController)
        Spacer(modifier = Modifier.height(20.dp))

        var cardNumber by remember { mutableStateOf(TextFieldValue(text = "12345")) }
        var amountValue by remember { mutableStateOf(TextFieldValue(text = "12345")) }
        val focusRequester = remember { FocusRequester() }

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
                .focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = cardNumber,
            visualTransformation = CardVisualTransformation(),
            onValueChange = {
                cardNumber = filterCard(cardNumber, it)
            }
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            value = amountValue,
            visualTransformation = AmountVisualTransformation(),
            onValueChange = {
                amountValue = it.copy(text = filterAmount(it.text))
            }
        )

        LaunchedEffect(Unit) {
            cardNumber = cardNumber.copy(selection = TextRange(cardNumber.text.length))
            focusRequester.requestFocus()
        }
    }
}

fun filterCard(old: TextFieldValue, input: TextFieldValue): TextFieldValue {
    if (input.text.length > 16) return old
    val filtered = input.text.filter { it.isDigit() }
    return if (filtered.length == input.text.length) {
        input
    } else {
        old
    }
}


fun filterAmount(input: String): String {
    var result = ""
    var containDoth = false
    var count = 0
    input.forEachIndexed() { idx, ch ->
        if (ch == '.') {
            if (idx == 0) return ""
            containDoth = true
        }
        if (containDoth) {
            count++
        }
        if (count > 3) {
            return result
        }
        if ((ch.isDigit()) || (ch == '.' && !result.contains('.'))) {
            result += ch
        }

    }
    return result
}

class CardVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 16) text.text.substring(0..15) else text.text
        var out = ""
        for (i in trimmed.indices) {
            out += trimmed[i]
            if (i % 4 == 3 && i != 15) out += " "
        }

        return TransformedText(AnnotatedString(out), object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 4) return offset
                if (offset <= 8) return offset + 1
                if (offset <= 12) return offset + 2
                if (offset <= 16) return offset + 3
                return 19
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 4) return offset
                if (offset <= 9) return offset - 1
                if (offset <= 14) return offset - 2
                if (offset <= 19) return offset - 3
                return 16
            }
        })
    }

}

class AmountVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val transformed = convertor(text.text)
        return TransformedText(AnnotatedString(transformed), object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset == 0) return 0
                if (offset >= text.length) return transformed.length
                var i = 0
                var o = 0
                while (i < offset) {
                    if (!transformed[o].isWhitespace()) {
                        i++
                    }
                    o++
                }
                return o
            }

            override fun transformedToOriginal(offset: Int): Int {
                return transformed.substring(0, offset).replace(" ", "").length
            }
        })
    }

    private fun convertor(amount: String, divider: String = " "): String {
        var result = ""
        val parts = amount.split('.')
        val input = parts.first().reversed()
        for (i in input.length - 1 downTo 0) {
            result += input[i]
            if (i % 3 == 0 && i != 0) result += divider
        }
        if (parts.size > 1) {
            result += '.' + parts[1]

        }
        return result
    }
}
