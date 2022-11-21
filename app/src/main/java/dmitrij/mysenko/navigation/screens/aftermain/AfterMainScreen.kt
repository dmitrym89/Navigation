package dmitrij.mysenko.navigation.screens.aftermain

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.shared.CurrentRoute

@Composable
fun AfterMainScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        CurrentRoute(navController = navController)
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                navController.navigate(Screen.TabsScreen.route){popUpTo(Screen.MainScreen.route)}
                navController.navigate(Screen.CustomTabsScreen.route)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Back to MainScreen and forward to CustomTabScreen")
        }

        val list = listOf("aaa", "bbb", "ccc")
        var selected by rememberSaveable {
            mutableStateOf(list[0])
        }
        Spacer(modifier = Modifier.height(100.dp))
        DropDownList(valuesList = list, valueToSelect = selected, selectedValue = {selected = it})

    }
}

@Composable
private fun DropDownList(
    valuesList: List<String>,
    valueToSelect: String,
    selectedValue: (String) -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box {
        Row(
            modifier = Modifier.clickable { expanded = !expanded },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = valueToSelect,
                maxLines = 1,
                textAlign = TextAlign.Center,
                modifier = Modifier.widthIn(256.dp)
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                tint = MaterialTheme.colors.onBackground,
                contentDescription = null
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            valuesList.forEach { value ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        selectedValue(value)
                    }
                ) {
                    Text(text = value, textAlign = TextAlign.Center)
                }
            }
        }
    }
}


