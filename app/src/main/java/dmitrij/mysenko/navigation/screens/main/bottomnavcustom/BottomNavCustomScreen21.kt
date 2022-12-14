package dmitrij.mysenko.navigation.screens.main.bottomnavcustom

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.shared.CurrentRoute
import kotlin.math.sqrt

@Composable
fun BottomNavCustomScreen21(
    rootNavController: NavController, navHostController: NavController,
    backClick: (NestedGraph) -> Unit
) {


    BackHandler {
        backClick.invoke(NestedGraph.BottomNavCustom2)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        CurrentRoute(navController = rootNavController, prefix = "Root current route: ")
        Spacer(modifier = Modifier.height(20.dp))
        CurrentRoute(navController = navHostController)
        Spacer(modifier = Modifier.height(20.dp))

        Graphhh(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .aspectRatio(1f)
                .background(color = Color.LightGray)
        )

        Button(
            onClick = {
                navHostController.navigate(Screen.BottomNavCustomScreen22.makeRoute(NestedGraph.BottomNavCustom2))
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Next screen in this tab")
        }
    }
}

@Composable
fun Graphhh(modifier: Modifier){
    Canvas(
        modifier = modifier,
        onDraw = {
            val sH = 2.dp.toPx()
            val sW = 2f * sH / sqrt(3f)
            val r = (size.width - 18 * sW) / 29
            val h = sqrt(3f) * r / 2

            val path = Path().apply {
                moveTo(-r / 2, -h)
                lineTo(r / 2, -h)
                lineTo(r, 0f)
                lineTo(r / 2, h)
                lineTo(-r / 2, h)
                lineTo(-r, 0f)
                close()
            }

            repeat(266) { i ->
                val x = i / 14
                val y = i % 14

                translate(
                    left = (x * 1.5f + 1f) * r + x * sW,
                    top = if (x % 2 == 0) {
                        (y * 2 + 2) * h + (y + 0.5f) * sH
                    } else {
                        (y * 2 + 1) * h + y * sH
                    }
                ) {
                    drawPath(path = path, color = Color.Red)
                }
            }
        })
}