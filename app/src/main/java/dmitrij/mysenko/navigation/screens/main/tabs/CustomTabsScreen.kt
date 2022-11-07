package dmitrij.mysenko.navigation.screens.main.tabs

import android.graphics.Paint
import android.text.TextPaint
import android.util.Log
import android.view.MotionEvent
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.shared.CurrentRoute

@Composable
fun CustomTabsScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        CurrentRoute(navController = navController)
        Spacer(modifier = Modifier.height(20.dp))

        val items = listOf("Tab #1", "Tab #2")
        val isLeftSelected = remember { mutableStateOf(true) }
        var sliderPosition by remember {
            mutableStateOf(1000f)
        }
        Text(text = "Animation duration $sliderPosition ms")
        Slider(
            value = sliderPosition,
            onValueChange = {sliderPosition = it},
            valueRange = 300f..4000f,
            modifier = Modifier.padding(horizontal = 50.dp)
        )
        Tabs(isLeftSelected = isLeftSelected, duration = sliderPosition.toInt())
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.weight(1f)
        ){
            Text(
                text = if (isLeftSelected.value) items[0] else items[1]
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun Tabs(
    text1: String = "Tab #1",
    text2: String = "Tab #2",
    duration: Int = 300,
    isLeftSelected: MutableState<Boolean>
) {
    var width = 0f
    var height = 0f

    val selectionPosition: Float by animateFloatAsState(
        targetValue = if (isLeftSelected.value) 0f else 1f,
        animationSpec = tween(
            durationMillis = duration,
            easing = CubicBezierEasing(.4f, .0f, .2f, .1f)
        ),
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = Color.LightGray)
    ) {
        Canvas(modifier = Modifier
            .fillMaxSize()
            .graphicsLayer(alpha = 0.99f)
            .pointerInteropFilter { event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    if (event.x <= width / 2 && !isLeftSelected.value) {
                        isLeftSelected.value = true
                    }
                    if (event.x > width / 2 && isLeftSelected.value) {
                        isLeftSelected.value = false
                    }
                }
                true
            }, onDraw = {

            width = size.width
            height = size.height

            val paint = TextPaint()
            paint.isAntiAlias = true
            paint.textSize = 40.sp.toPx()
            paint.color = Color.Black.toArgb()
            paint.textAlign = Paint.Align.CENTER
            paint.isFakeBoldText = true

            drawIntoCanvas {
                it.nativeCanvas.drawText(text1, width / 4, height / 2 + 15.sp.toPx(), paint)
                it.nativeCanvas.drawText(
                    text2,
                    3 * width / 4,
                    height / 2 + 15.sp.toPx(),
                    paint
                )
            }

            drawRoundRect(
                color = Color.Red,
                cornerRadius = CornerRadius(15.dp.toPx()),
                topLeft = Offset(15.dp.toPx() + selectionPosition * width / 2, 15.dp.toPx()),
                size = Size(width / 2 - 30.dp.toPx(), height - 30.dp.toPx()),
                alpha = 1f,
                blendMode = BlendMode.SrcIn
            )

            drawRoundRect(
                color = Color.Blue,
                cornerRadius = CornerRadius(15.dp.toPx()),
                topLeft = Offset(15.dp.toPx() + selectionPosition * width / 2, 15.dp.toPx()),
                size = Size(width / 2 - 30.dp.toPx(), height - 30.dp.toPx()),
                alpha = 1f,
                blendMode = BlendMode.DstOver
            )
        })
    }
}