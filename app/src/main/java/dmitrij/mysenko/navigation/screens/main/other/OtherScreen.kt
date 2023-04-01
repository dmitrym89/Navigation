package dmitrij.mysenko.navigation.screens.main.other

import android.provider.ContactsContract.Contacts
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.shared.CurrentRoute
import dmitrij.mysenko.navigation.shared.PositionProviderPopup
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select

@Composable
fun OtherScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 100.dp)
    ) {
        CurrentRoute(navController = navController)
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.WallpaperScreen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Wallpaper")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.GridLayoutScreen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "GridLayout")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.DragAndDropScreen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "DragAndDrop")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.CollapsingScreen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Collapsing")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.IosActivityScreen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "iOS Activity")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.MasksScreen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Masks")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.Player13Screen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Player13")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.TableScreen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Table")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.ChartScreen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Chart")
        }
    }
}

