package dmitrij.mysenko.navigation.screens.main.other.grid


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.shared.CurrentRoute
import kotlin.random.Random

@Composable
fun GridLayoutScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        CurrentRoute(navController = navController)
        Spacer(modifier = Modifier.height(20.dp))
        GridLayout(
            columnCount = 4,
            space = 10.dp,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(10.dp)
        ) {
            Text(
                text = "1",
                modifier = Modifier
                    .fillMaxSize()
                    .columnSpan(2, fill = false)
                    .background(color = Color(Random.nextInt()))
            )
            Text(
                text = "2",
                modifier = Modifier
                    .fillMaxSize()
                    .columnSpan(2)
                    .background(color = Color(Random.nextInt()))
            )
            Text(
                text = "3",
                modifier = Modifier
                    .fillMaxSize()
                    .columnSpan(3)
                    .background(color = Color(Random.nextInt()))
            )
            Text(
                text = "4",
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(Random.nextInt()))
            )
            Text(
                text = "5",
                modifier = Modifier
                    .fillMaxSize()
                    .rowSpan(2)
                    .columnSpan(2)
                    .background(color = Color(Random.nextInt()))
            )
            Text(
                text = "6",
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(Random.nextInt()))
            )
            Text(
                text = "7",
                modifier = Modifier
                    .fillMaxSize()
                    .rowSpan(2)
                    .background(color = Color(Random.nextInt()))
            )
            Text(
                text = "8",
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(Random.nextInt()))
            )
        }
    }
}



