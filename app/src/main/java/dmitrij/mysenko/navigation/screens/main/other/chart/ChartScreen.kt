package dmitrij.mysenko.navigation.screens.main.other.chart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.shared.CurrentRoute
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

@Composable
fun ChartScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        CurrentRoute(navController = navController)
        Spacer(modifier = Modifier.height(20.dp))

        Chart(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .aspectRatio(1f),
        )
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
private fun Chart(
    modifier: Modifier = Modifier,
    fontColor: Color = Color.DarkGray,
    fontSize: TextUnit = TextUnit.Unspecified,
    values: List<Float> = listOf(40f, 20f, 10f, 20f)
) {
    require(values.isNotEmpty()) { "List of values must not be empty" }
    val sum = values.sum()
    val data = values.sortedDescending().map {
        ChartData("${String.format("%.1f", 100 * it / sum)}%", 360 * it / sum)
    }

    val fontFamilyResolver = LocalFontFamilyResolver.current
    val density = LocalDensity.current
    val style =
        LocalTextStyle.current.copy(color = fontColor)
    val textMeasurer = TextMeasurer(
        fallbackFontFamilyResolver = fontFamilyResolver,
        fallbackLayoutDirection = LayoutDirection.Ltr,
        fallbackDensity = density
    )

    Canvas(
        modifier = modifier.graphicsLayer { alpha = 0.99f },
        onDraw = {
            val thickness = size.width / 4
            val strokeStyle = Stroke(width = thickness, cap = StrokeCap.Butt)
            val sizeWithThickness = Size(size.width - thickness, size.height - thickness)
            val topLeft = Offset(thickness / 2, thickness / 2)
            var startAngle = 90f
            val textStyle = style.copy(
                fontSize = if (fontSize == TextUnit.Unspecified) {
                    (thickness / 4).toSp()
                } else {
                    fontSize
                }
            )
            val r = (size.width - thickness) / 2
            val radiansCoef = 0.017453292f


            data.forEach { chartData ->
                drawArc(
                    color = Color(Random.nextLong()),
                    startAngle = startAngle,
                    sweepAngle = chartData.degrees,
                    useCenter = false,
                    topLeft = topLeft,
                    size = sizeWithThickness,
                    style = strokeStyle
                )

                startAngle += chartData.degrees
            }

            startAngle = 90f
            data.forEach { chartData ->
                val angle = radiansCoef * startAngle
                val x = center.x + size.width/2 * cos(angle)
                val y = center.y + size.width/2 * sin(angle)

                drawLine(
                    color = Color.Transparent,
                    start = center,
                    end = Offset(x, y),
                    strokeWidth = 5.dp.toPx(),
                    blendMode = BlendMode.DstIn
                )

                startAngle += chartData.degrees
            }

            startAngle = 90f
            data.forEach { chartData ->
                val textLayoutResult = textMeasurer.measure(
                    text = AnnotatedString(chartData.percent),
                    style = textStyle,
                )

                val angle = radiansCoef * (startAngle + chartData.degrees / 2)
                val x = center.x + r * cos(angle)
                val y = center.y + r * sin(angle)

                drawText(
                    textLayoutResult = textLayoutResult,
                    topLeft = Offset(
                        x - textLayoutResult.size.width / 2,
                        y - textLayoutResult.size.height / 2
                    )
                )

                startAngle += chartData.degrees
            }
        }
    )
}

private data class ChartData(
    val percent: String,
    val degrees: Float
)


