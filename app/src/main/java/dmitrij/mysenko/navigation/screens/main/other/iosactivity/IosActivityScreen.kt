package dmitrij.mysenko.navigation.screens.main.other.iosactivity

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.shared.CurrentRoute
import kotlin.math.PI

@Composable
fun IosActivityScreen(navController: NavController) {
    Column(
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        CurrentRoute(navController = navController)
        Spacer(modifier = Modifier.height(20.dp))

        var r by remember { mutableStateOf(Pair(0f, 0f)) }
        var g by remember { mutableStateOf(Pair(0f, 0f)) }
        var b by remember { mutableStateOf(Pair(0f, 0f)) }

        val ra: Float by animateFloatAsState(
            targetValue = r.second,
            animationSpec = tween(
                durationMillis = (r.second - r.first).toInt() * 3,
                delayMillis = 0,
                easing = FastOutSlowInEasing
            )
        )
        val ga: Float by animateFloatAsState(
            targetValue = g.second,
            animationSpec = tween(
                durationMillis = (g.second - g.first).toInt() * 3,
                delayMillis = 0,
                easing = FastOutSlowInEasing
            )
        )
        val ba: Float by animateFloatAsState(
            targetValue = b.second,
            animationSpec = tween(
                durationMillis = (b.second - b.first).toInt() * 3,
                delayMillis = 0,
                easing = FastOutSlowInEasing
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            TextField(
                value = if (r.second == 0f) "" else r.second.toInt().toString(),
                onValueChange = { r = Pair(r.second, it.trim().toFloat()) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(20.dp))
            TextField(
                value = if (g.second == 0f) "" else g.second.toInt().toString(),
                onValueChange = { g = Pair(g.second, it.trim().toFloat()) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(20.dp))
            TextField(
                value = if (b.second == 0f) "" else b.second.toInt().toString(),
                onValueChange = { b = Pair(b.second, it.trim().toFloat()) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Canvas(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .aspectRatio(1f)
                .background(color = Color.Black)
                .graphicsLayer(alpha = 0.99f),
            onDraw = {
                val width = size.width
                val thickness = width / 10
                val space = 5.dp.toPx()
                val p1out = 0f
                val p1in = p1out + thickness
                val p2out = p1in + space
                val p2in = p2out + thickness
                val p3out = p2in + space
                val p3in = p3out + thickness
                val startAngle = 0f
                val o1 = (p1in + p1out) / 2
                val o2 = (p2in + p2out) / 2
                val o3 = (p3in + p3out) / 2
                val d1 = width - 2 * o1
                val d2 = width - 2 * o2
                val d3 = width - 2 * o3
                val color1start = Color(0xfffa3333)
                val color1end = Color(0xfffa4477)
                val color2start = Color(0xffa9f042)
                val color2end = Color(0xfffcfc5d)
                val color3start = Color(0xff4eacf0)
                val color3end = Color(0xff50f0c5)

                val path1 = getRing(p1out, p1in, width)
                val path2 = getRing(p2out, p2in, width)
                val path3 = getRing(p3out, p3in, width)

                val value1 = ra * 360 / 100
                val value2 = ga * 360 / 100
                val value3 = ba * 360 / 100

                drawPath(
                    path = path1,
                    color = Color(0xff500000),
                )
                drawPath(
                    path = path2,
                    color = Color(0xff005000),
                )
                drawPath(
                    path = path3,
                    color = Color(0xff000050),
                )

                rotate(degrees = -90f) {
                    if (value1 != 0f) {
                        rotate(
                            degrees = if (value1 > 360f) {
                                value1 % 360
                            } else {
                                0f
                            }
                        ) {
                            drawRing(
                                colorStart = color1start,
                                colorEnd = color1end,
                                startAngle = startAngle,
                                sweepAngle = if (value1 > 360f) 360f else value1,
                                ringOffset = o1,
                                ringDiameter = d1,
                                ringThickness = thickness,
                                offsetStart = Offset(width - thickness, (width - thickness) / 2),
                                halfSize = width / 2
                            )
                        }
                    }
                    if (value2 != 0f) {
                        rotate(
                            degrees = if (value2 > 360f) {
                                value2 % 360
                            } else {
                                0f
                            }
                        ) {
                            drawRing(
                                colorStart = color2start,
                                colorEnd = color2end,
                                startAngle = startAngle,
                                sweepAngle = if (value2 > 360f) 360f else value2,
                                ringOffset = o2,
                                ringDiameter = d2,
                                ringThickness = thickness,
                                offsetStart = Offset(
                                    width - 2 * thickness - space,
                                    (width - thickness) / 2
                                ),
                                halfSize = width / 2
                            )
                        }
                    }
                    if (value3 != 0f) {
                        rotate(
                            degrees = if (value3 > 360f) {
                                value3 % 360
                            } else {
                                0f
                            }
                        ) {
                            drawRing(
                                colorStart = color3start,
                                colorEnd = color3end,
                                startAngle = startAngle,
                                sweepAngle = if (value3 > 360f) 360f else value3,
                                ringOffset = o3,
                                ringDiameter = d3,
                                ringThickness = thickness,
                                offsetStart = Offset(
                                    width - 3 * thickness - 2 * space,
                                    (width - thickness) / 2
                                ),
                                halfSize = width / 2
                            )
                        }
                    }
                }
            })
    }
}

private fun getRing(pOut: Float, pIn: Float, width: Float): Path {
    return Path().apply {
        op(
            path1 = Path().apply {
                addOval(oval = Rect(pOut, pOut, width - pOut, width - pOut))
            },
            path2 = Path().apply {
                addOval(oval = Rect(pIn, pIn, width - pIn, width - pIn))
            },
            operation = PathOperation.Difference
        )
    }
}

fun DrawScope.drawRing(
    colorStart: Color,
    colorEnd: Color,
    startAngle: Float,
    sweepAngle: Float,
    ringOffset: Float,
    ringDiameter: Float,
    ringThickness: Float,
    offsetStart: Offset,
    halfSize: Float
) {
    val limit = 360f * (1 - ringThickness / PI.toFloat() / ringDiameter)
    drawArc(
        brush = Brush.sweepGradient(listOf(colorStart, colorEnd)),
        startAngle = startAngle,
        sweepAngle = sweepAngle,
        useCenter = false,
        topLeft = Offset(ringOffset, ringOffset),
        size = Size(ringDiameter, ringDiameter),
        style = Stroke(
            width = ringThickness,
            cap = StrokeCap.Round
        )
    )
    drawArc(
        color = colorStart,
        startAngle = 180f,
        sweepAngle = 180f,
        useCenter = true,
        topLeft = offsetStart,
        size = Size(ringThickness, ringThickness)
    )

    if (sweepAngle > limit) {
        val gradientCenter = sweepAngle / 360f
        val gradientHalf = (1 - limit / 360f) / 2
        rotate(degrees = sweepAngle + 90f - .5f) {
            val shadowRadius = 1.3f * ringThickness / 2
            drawArc(
                brush = Brush.radialGradient(
                    0.5f to Color(0xaa000000),
                    1f to Color(0x00000000),
                    center = Offset(halfSize, ringOffset),
                    radius = shadowRadius
                ),
                startAngle = 270f,
                sweepAngle = 180f,
                useCenter = true,
                size = Size(shadowRadius * 2, shadowRadius * 2),
                topLeft = Offset(
                    halfSize - shadowRadius,
                    ringOffset - shadowRadius
                )
            )
            drawCircle(
                brush = Brush.linearGradient(
                    colors = listOf(
                        lerp(colorStart, colorEnd, gradientCenter - gradientHalf),
                        lerp(colorStart, colorEnd, gradientCenter + gradientHalf)
                    ),
                    start = Offset(halfSize - ringThickness / 2, ringOffset),
                    end = Offset(halfSize + ringThickness / 2, ringOffset)
                ),
                radius = ringThickness / 2,
                center = Offset(halfSize, ringOffset)
            )
        }
    }
}
