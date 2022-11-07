package dmitrij.mysenko.navigation.screens.main.bottomnavcustom

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.shared.CurrentRoute

@Composable
fun BottomNavCustomScreen11(
    rootNavController: NavController, navHostController: NavController,
    backClick: (NestedGraph) -> Unit,
    contentBottomPadding: Dp
) {

    BackHandler {
        backClick.invoke(NestedGraph.BottomNavCustom1)
    }

    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(bottom = contentBottomPadding).statusBarsPadding().navigationBarsPadding()
    ) {

        CurrentRoute(navController = rootNavController, prefix = "Root current route: ")
        Spacer(modifier = Modifier.height(20.dp))
        CurrentRoute(navController = navHostController)
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navHostController.navigate(Screen.BottomNavCustomScreen12.makeRoute(NestedGraph.BottomNavCustom1))
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Next screen in this tab")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            fontSize = 24.sp,
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam pretium dapibus magna in maximus. Sed purus magna, pellentesque id nulla eget, blandit ullamcorper libero. Suspendisse elementum mi orci, id finibus lectus venenatis id. Donec pretium erat sagittis sollicitudin imperdiet. Curabitur bibendum odio ac aliquet gravida. Maecenas non tincidunt dolor. Sed at metus convallis, dignissim orci in, rhoncus lorem. Nam at odio dapibus, consectetur mauris ac, tincidunt ex.\n" +
                    "\n" +
                    "Vivamus a odio quis risus pulvinar tristique. Curabitur volutpat eleifend ligula, ac ullamcorper elit blandit vitae. Nam tincidunt quam ac diam volutpat, sed efficitur libero bibendum. Quisque tristique mauris eu nisi tincidunt lobortis. Quisque condimentum risus a faucibus porttitor. Quisque in odio sit amet sem gravida rhoncus. Donec lacinia libero quis magna ultrices, eget suscipit quam laoreet. Praesent tristique purus a eros consequat, euismod commodo tellus ullamcorper. Praesent vestibulum, ex suscipit pellentesque volutpat, erat dui condimentum lacus, ac vestibulum velit libero sit amet dolor.\n" +
                    "\n" +
                    "Maecenas luctus enim sit amet eros maximus, sit amet consectetur nisi maximus. Pellentesque libero dui, blandit in nisl vehicula, accumsan auctor turpis. Nam lobortis pulvinar iaculis. Nam semper magna vel turpis auctor, eget consequat nibh placerat. Pellentesque a augue vitae magna mollis fermentum a in diam. Nunc non tellus ut sapien luctus lobortis id at metus. Suspendisse sit amet vehicula lectus. Quisque eu mollis ipsum. Maecenas tincidunt ac leo malesuada pretium. Integer ex tellus, pharetra eu nisl et, malesuada consectetur tellus. Nunc luctus justo at nibh volutpat rhoncus.\n" +
                    "\n" +
                    "Aliquam vehicula, mi in lacinia malesuada, felis ex tempus tellus, ac tempus massa neque et enim. Proin tincidunt imperdiet tellus et scelerisque. Donec ut iaculis est. Suspendisse lobortis auctor sapien iaculis semper. Nulla ac dolor nec turpis ornare aliquet. Vestibulum commodo fringilla elit a viverra. Nulla facilisi. Nunc pellentesque vitae orci at maximus."
        )
    }
}