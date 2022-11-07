package dmitrij.mysenko.navigation.shared

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.window.PopupProperties
import java.util.*

@Composable
fun SimplePopup(
    state: MutableState<Boolean>,
    alignment: Alignment = Alignment.Center,
    text: String = "Simple popup. \nTap to close",
    dismissOnClickOutside: Boolean = false
) {
    if (state.value) {
        Popup(
            alignment = alignment,
            offset = IntOffset(0, 0),
            onDismissRequest = { state.value = false },
            properties = PopupProperties(
                dismissOnClickOutside = dismissOnClickOutside
            )
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = Color.Yellow)
                    .clickable { state.value = false }
                    .padding(all = 10.dp)
            ) {
                Text(text = text)
            }
        }
    }
}

@Composable
fun PositionProviderPopup(
    state: MutableState<Boolean>,
    text: String = "Popup with position provider. \nTap to close",
    dismissOnClickOutside: Boolean = false,
    align: MyPopupAlign = MyPopupAlign.UnderStart
) {
    if (state.value) {
        Popup(
            popupPositionProvider = MyPopupPositionProvider(align),
            onDismissRequest = { state.value = false },
            properties = PopupProperties(
                dismissOnClickOutside = dismissOnClickOutside
            )
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = Color.Yellow)
                    .clickable { state.value = false }
                    .padding(all = 10.dp)
            ) {
                Text(text = text, color = Color.Black)
            }
        }
    }
}

private class MyPopupPositionProvider(val align: MyPopupAlign) : PopupPositionProvider {
    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize
    ): IntOffset {
        val x = when(align.horizontalAlign){
            MyPopupHorizontalAlign.START -> anchorBounds.left
            MyPopupHorizontalAlign.END ->anchorBounds.right-popupContentSize.width
        }
        val y = when(align.verticalAlign){
            MyPopupVerticalAlign.UNDER -> anchorBounds.bottom
            MyPopupVerticalAlign.BEFORE -> anchorBounds.top - popupContentSize.height
        }
        return IntOffset(x, y)
    }
}

enum class MyPopupVerticalAlign {
    UNDER, BEFORE
}

enum class MyPopupHorizontalAlign {
    START, END
}

data class MyPopupAlign(
    val verticalAlign: MyPopupVerticalAlign,
    val horizontalAlign: MyPopupHorizontalAlign
) {

    fun name() = "${
        verticalAlign.name.lowercase().replaceFirstChar { it.uppercase() }
    }${horizontalAlign.name.lowercase().replaceFirstChar { it.uppercase() }}"

    companion object {
        @Stable
        val UnderStart = MyPopupAlign(MyPopupVerticalAlign.UNDER, MyPopupHorizontalAlign.START)

        @Stable
        val UnderEnd = MyPopupAlign(MyPopupVerticalAlign.UNDER, MyPopupHorizontalAlign.END)

        @Stable
        val BeforeStart = MyPopupAlign(MyPopupVerticalAlign.BEFORE, MyPopupHorizontalAlign.START)

        @Stable
        val BeforeEnd = MyPopupAlign(MyPopupVerticalAlign.BEFORE, MyPopupHorizontalAlign.END)
    }
}
