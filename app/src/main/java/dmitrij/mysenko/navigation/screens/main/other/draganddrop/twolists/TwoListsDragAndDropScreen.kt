package dmitrij.mysenko.navigation.screens.main.other.draganddrop.twolists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.shared.CurrentRoute

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TwoListsDragAndDropScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        CurrentRoute(navController = navController)
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = Color.Black)
        )
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(all = 4.dp)
        ) {
            repeat(10) { i ->
                Text(
                    text = "item $i",
                    fontSize = 24.sp,
                    modifier = Modifier
                        .background(color = if (i % 2 == 1) Color.Green else Color.Red)
                        .padding(all = 4.dp)
                        .background(color = Color.LightGray)
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
            //.background(color = Color.Black)
        )

        Box(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .background(color = Color.LightGray)
                .leftBorder()
        ) {
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam pretium dapibus magna in maximus. Sed purus magna, pellentesque id nulla eget, blandit ullamcorper libero. Suspendisse elementum mi orci, id finibus lectus venenatis id. Donec pretium erat sagittis sollicitudin imperdiet. Curabitur bibendum odio ac aliquet gravida. Maecenas non tincidunt dolor. Sed at metus convallis, dignissim orci in, rhoncus lorem. Nam at odio dapibus, consectetur mauris ac, tincidunt ex.",
            )
        }
    }
}

fun Modifier.leftBorder() = composed(
    factory = {
        val density = LocalDensity.current.density
        Modifier.drawBehind {
            drawLine(
                color = Color.Red,
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = 0f, y = size.height),
                strokeWidth = 5 * density
            )
        }
    }
)





