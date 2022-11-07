package dmitrij.mysenko.navigation.screens.main.bottomnavcustom

import android.graphics.drawable.shapes.OvalShape
import androidx.annotation.StringRes
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dmitrij.mysenko.navigation.R
import dmitrij.mysenko.navigation.navigation.NestedGraph
import dmitrij.mysenko.navigation.screens.Screen


@Composable
fun BottomNavCustomScreen(rootNavController: NavController) {

    val navHostController = rememberNavController()

    val items = listOf(
        BottomNavCustomItem.BottomNavItem1,
        BottomNavCustomItem.BottomNavItem2,
        BottomNavCustomItem.BottomNavItem3
    )

    val systemUiController = rememberSystemUiController()

    DisposableEffect(systemUiController) {
        systemUiController.setNavigationBarColor(
            color = Color.Magenta,
            darkIcons = false
        )
        onDispose {
            systemUiController.setNavigationBarColor(
                color = Color(0x88000000),
                darkIcons = false
            )
        }
    }

    val tabStack = remember {
        mutableStateListOf<NestedGraph>(NestedGraph.BottomNavCustom1)
    }

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val hierarchy = currentDestination?.hierarchy
    val selected = when {
        hierarchy?.any { it.route == BottomNavCustomItem.BottomNavItem1.graph.route } == true -> 0
        hierarchy?.any { it.route == BottomNavCustomItem.BottomNavItem2.graph.route } == true -> 1
        hierarchy?.any { it.route == BottomNavCustomItem.BottomNavItem3.graph.route } == true -> 2
        else -> 0
    }

    NavHost(
        navHostController,
        startDestination = NestedGraph.BottomNavCustom1.route,
        Modifier.fillMaxSize()
    ) {
        bottomNavCustomGraph1(
            rootNavController = rootNavController,
            navHostController = navHostController,
            backClick = { backClick(rootNavController, navHostController, tabStack, it) },
            90.dp
        )
        bottomNavCustomGraph2(
            rootNavController = rootNavController,
            navHostController = navHostController,
            backClick = { backClick(rootNavController, navHostController, tabStack, it) }
        )
        bottomNavCustomGraph3(
            rootNavController = rootNavController,
            navHostController = navHostController,
            backClick = { backClick(rootNavController, navHostController, tabStack, it) }
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        CustomBottomBar(
            selected = selected,
            count = items.size,
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .height(90.dp)
                .align(Alignment.BottomCenter)

        ) {
            items.forEachIndexed { index, bottomNavItem ->
                CustomBottomNavigationItem(
                    icon = bottomNavItem.icon,
                    label = stringResource(bottomNavItem.resourceId),
                    badgeText = bottomNavItem.badgeText,
                    selectedContentColor = Color.Yellow,
                    unselectedContentColor = Color.DarkGray,
                    selected = selected == index,
                    onClick = {
                        if (tabStack.last() != bottomNavItem.graph) {
                            tabStack.remove(bottomNavItem.graph)
                            tabStack.add(bottomNavItem.graph)
                        }
                        nav(
                            navHostController = navHostController,
                            destination = bottomNavItem.graph.route
                        )
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
private fun RowScope.CustomBottomNavigationItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    badgeText: String = "",
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    selectedContentColor: Color = LocalContentColor.current,
    unselectedContentColor: Color = selectedContentColor.copy(alpha = ContentAlpha.medium)
) {
    val animationProgress by animateFloatAsState(
        targetValue = if (selected) 1f else 0f,
        animationSpec = TweenSpec(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    )

    val color = lerp(unselectedContentColor, selectedContentColor, animationProgress)
    val ripple = rememberRipple(bounded = false, radius = 40.dp, color = selectedContentColor)
    Box(
        modifier
            .selectable(
                selected = selected,
                onClick = onClick,
                enabled = true,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = ripple
            )
            .weight(1f)
    ) {

//        val fontFamilyResolver = LocalFontFamilyResolver.current
//        val density = LocalDensity.current
//        val style = LocalTextStyle.current.copy(color = color, textAlign = TextAlign.Center)
//        val textMeasurer = TextMeasurer(
//            fallbackFontFamilyResolver = fontFamilyResolver,
//            fallbackLayoutDirection = LayoutDirection.Ltr,
//            fallbackDensity = density
//        )
//        val textLayoutResult = textMeasurer.measure(
//            text = AnnotatedString(label),
//            style = style
//        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(2f - animationProgress.times(1.5f)))
            Box(modifier = Modifier) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier
                        .padding(top = 6.dp, start = 6.dp, end = 6.dp)
                        .align(Alignment.Center)
                )
                if (badgeText.isNotEmpty()) {
                    Text(
                        text = badgeText,
                        fontSize = 10.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .size(16.dp)
                            .background(color = Color.Blue, shape = RoundedCornerShape(50))
                            .align(Alignment.TopEnd)
                    )
                }
            }
            Text(text = label, color = color)
//            Canvas(
//                modifier = Modifier
//                    .width((textLayoutResult.size.width.div(density.density)).dp)
//                    .height(20.dp)
//            ) {
//                drawText(
//                    textLayoutResult = textLayoutResult,
//                    color = color
//                )
//            }
            Spacer(modifier = modifier.weight(1f))
        }
    }
}

@Composable
private fun CustomBottomBar(
    modifier: Modifier = Modifier,
    selected: Int = 0,
    count: Int = 3,
    content: @Composable RowScope.() -> Unit
) {
    val animationProgress by animateFloatAsState(
        targetValue = selected.toFloat(),
        animationSpec = TweenSpec(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    )
    Box(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val width = size.width
            val height = size.height
            val segmentWidth = width.div(count)
            val offset = segmentWidth.times(animationProgress)
            val halfSegmentWidth = offset + segmentWidth.times(.5f)
            val quarterSegmentWidth = offset + segmentWidth.times(.25f)
            val threeQuarterSegmentWidth = offset + segmentWidth.times(.75f)
            val barTopOffset = height.times(.2f)
            val shadow1 = 2.dp.toPx()

            val path = Path().apply {
                moveTo(0f, height)
                lineTo(0f, barTopOffset + shadow1)
                lineTo(offset, barTopOffset + shadow1)
                cubicTo(
                    quarterSegmentWidth, barTopOffset + shadow1,
                    quarterSegmentWidth, shadow1,
                    halfSegmentWidth, shadow1
                )
                cubicTo(
                    threeQuarterSegmentWidth, shadow1,
                    threeQuarterSegmentWidth, barTopOffset + shadow1,
                    offset + segmentWidth, barTopOffset + shadow1
                )
                lineTo(width, barTopOffset + shadow1)
                lineTo(width, height)
                lineTo(0f, height)
            }

            val shadow1Path = Path().apply {
                moveTo(0f, height)
                lineTo(0f, barTopOffset)
                lineTo(offset, barTopOffset)
                cubicTo(
                    quarterSegmentWidth, barTopOffset,
                    quarterSegmentWidth, 0f,
                    halfSegmentWidth, 0f
                )
                cubicTo(
                    threeQuarterSegmentWidth, 0f,
                    threeQuarterSegmentWidth, barTopOffset,
                    offset + segmentWidth, barTopOffset
                )
                lineTo(width, barTopOffset)
                lineTo(width, height)
                lineTo(0f, height)
            }

            drawPath(path = shadow1Path, color = Color(0x20802080))
            drawPath(path = path, color = Color.Magenta)

        }
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 4.dp)
        ) {
            content()
        }
    }
}

sealed class BottomNavCustomItem(
    val screen: Screen,
    val icon: ImageVector,
    val badgeText: String,
    @StringRes val resourceId: Int,
    val graph: NestedGraph
) {
    object BottomNavItem1 : BottomNavCustomItem(
        Screen.BottomNavCustomScreen11,
        Icons.Filled.Favorite,
        "",
        R.string.bottom_nav_item_1,
        NestedGraph.BottomNavCustom1
    )

    object BottomNavItem2 : BottomNavCustomItem(
        Screen.BottomNavCustomScreen21,
        Icons.Filled.AccountBox,
        " ",
        R.string.bottom_nav_item_2,
        NestedGraph.BottomNavCustom2
    )

    object BottomNavItem3 : BottomNavCustomItem(
        Screen.BottomNavCustomScreen31,
        Icons.Filled.List,
        "5",
        R.string.bottom_nav_item_3,
        NestedGraph.BottomNavCustom3
    )
}

private fun backClick(
    rootNavController: NavController,
    navHostController: NavController,
    tabStack: SnapshotStateList<NestedGraph>,
    graph: NestedGraph
) {
    when (val size = tabStack.size) {
        0 -> rootNavController.popBackStack()
        1 -> if (graph == NestedGraph.BottomNavCustom1) {
            rootNavController.popBackStack()
        } else {
            navHostController.navigate(NestedGraph.BottomNavCustom1.route)
        }
        else -> {
            tabStack.removeAt(size - 1)
            nav(navHostController = navHostController, destination = tabStack[size - 2].route)
        }
    }
}

private fun nav(navHostController: NavController, destination: String) {
    navHostController.navigate(destination) {
        popUpTo(navHostController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}