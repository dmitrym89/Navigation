package dmitrij.mysenko.navigation.screens.main.other.player13

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Slider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.shared.CurrentRoute
import dmitrij.mysenko.navigation.R
import kotlin.math.sin

@Composable
fun Player13Screen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        CurrentRoute(navController = navController)

        Spacer(modifier = Modifier.height(20.dp))

        var progress by remember {
            mutableStateOf(0.5f)
        }

        Slider(
            value = progress,
            onValueChange = { progress = it },
            valueRange = 0f..1f,
            modifier = Modifier.padding(50.dp)
        )

        PlayerSin(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = Color.DarkGray),
            progress = progress
        ){
            progress = it
        }
    }
}

@Composable
private fun PlayerSin(
    modifier: Modifier,
    progress: Float,
    radius: Dp = 15.dp,
    strokeWidth: Dp = 2.dp,
    waveWidth: Dp = 3.dp,
    waveHeight: Dp = 5.dp,
    waveStep: Dp = 30.dp,
    color: Color = Color.White,
    speed: Int = 1000,
    changeProgress: (Float) -> Unit
) {
    val yValues: List<Float> by remember(key1 = waveStep) {
        mutableStateOf(
            getList(waveStep.value.toInt())
        )
    }

    val infiniteTransition = rememberInfiniteTransition()
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = waveStep.value,
        animationSpec = infiniteRepeatable(
            animation = tween(speed, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Row(
        modifier = modifier
    ) {
        Icon(
            painterResource(id = R.drawable.skip_prev),
            contentDescription = "previous",
            tint = color
        )

        Canvas(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(horizontal = 5.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            changeProgress(it.x/size.width)
                        }
                    )
                }
                .pointerInput(Unit) {
                    detectHorizontalDragGestures { change, _ ->
                        changeProgress((change.position.x/size.width).coerceIn(0f,1f))
                    }
                },
            onDraw = {
                val centerY = size.center.y
                val step = 1.dp.toPx()
                val steps = yValues.size
                val waveH = waveHeight.toPx()

                val path = Path().apply {
                    var i = 0
                    moveTo(0f, centerY + waveH * yValues[offset.toInt()])
                    while (i * step < size.width * progress) {
                        i++
                        lineTo(step * i, centerY + waveH * yValues[(i + offset.toInt()) % steps])
                    }
                }

                drawPath(
                    path = path,
                    color = color,
                    style = Stroke(
                        width = waveWidth.toPx(),
                        cap = StrokeCap.Round
                    )
                )

                drawLine(
                    color = color,
                    start = Offset(size.width * progress, centerY),
                    end = Offset(size.width, centerY),
                    strokeWidth = strokeWidth.toPx()
                )

                drawCircle(
                    color = color,
                    radius = radius.toPx(),
                    center = Offset(size.width * progress, centerY)
                )
            })

        Icon(
            painterResource(id = R.drawable.skip_next),
            contentDescription = "next",
            tint = color
        )
    }
}

private fun getList(steps: Int): List<Float> {
    val values = mutableListOf<Float>()
    for (x in 0 until steps) {
        values.add(sin(2 * x * Math.PI / steps).toFloat())
    }
    return values
}



