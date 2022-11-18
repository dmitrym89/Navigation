package dmitrij.mysenko.navigation.screens.main.drawer

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dmitrij.mysenko.navigation.R
import dmitrij.mysenko.navigation.navigation.NestedGraph
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun DrawerScreen(rootNavController: NavController) {

    val systemUiController = rememberSystemUiController()
    val color = MaterialTheme.colors.primary
    DisposableEffect(systemUiController) {
        systemUiController.setStatusBarColor(
            color = color,
            darkIcons = false
        )
        onDispose {
            systemUiController.setStatusBarColor(
                color = Color.Transparent,
                darkIcons = true
            )
        }
    }

    val navHostController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val items = listOf(
        DrawerItem.Top,
        DrawerItem.Latest
    )
    val closeDrawer = {
        scope.launch {
            scaffoldState.drawerState.close()
        }
    }
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = "Drawer") },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        },
                    ) {
                        Icon(Icons.Rounded.Menu, contentDescription = "")
                    }
                },
                modifier = Modifier.fillMaxWidth().statusBarsPadding()
            )
        },
        drawerContent = {
            DrawerContent(
                items = items,
                currentDestination = currentDestination,
                navHostController = navHostController,
                closeDrawer = closeDrawer
            )
        }
    ) { innerPadding ->
        NavHost(
            navHostController,
            startDestination = NestedGraph.DrawerTop.route,
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            drawerTopGraph(
                rootNavController = rootNavController,
                navHostController = navHostController
            )
            drawerLatestGraph(
                rootNavController = rootNavController,
                navHostController = navHostController
            )
        }
    }
}

@Composable
private fun DrawerContent(
    items: List<DrawerItem>,
    currentDestination: NavDestination?,
    navHostController: NavController,
    closeDrawer: () -> Job
) {
    Column {
        Spacer(modifier = Modifier.statusBarsPadding())
        items.forEach { item ->
            DrawerItem(
                label = stringResource(item.resourceId),
                isSelected = currentDestination?.hierarchy?.any { it.route == item.graph.route } == true
            ) {
                navHostController.navigate(item.graph.route) {
                    popUpTo(navHostController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                closeDrawer.invoke()
            }
        }
    }
}

@Composable
private fun DrawerItem(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Text(text = label,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = if (isSelected) Color.Cyan else Color.Transparent)
            .clickable { onClick.invoke() }
            .padding(16.dp)

    )
}

sealed class DrawerItem(
    //val screen: Screen,
    @StringRes val resourceId: Int,
    val graph: NestedGraph
) {
    object Top : DrawerItem(
        //Screen.DrawerTopScreen,
        R.string.drawer_item_top,
        NestedGraph.DrawerTop
    )

    object Latest : DrawerItem(
        //Screen.DrawerLatestScreen,
        R.string.drawer_item_latest,
        NestedGraph.DrawerLatest
    )
}