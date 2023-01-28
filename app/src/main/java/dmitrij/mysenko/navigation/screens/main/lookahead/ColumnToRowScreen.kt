@file:OptIn(
    ExperimentalComposeUiApi::class,
    ExperimentalMaterialApi::class
)

package dmitrij.mysenko.navigation.screens.main.lookahead

import android.content.res.Resources
import android.content.res.XmlResourceParser
import android.util.AttributeSet
import android.util.Log
import android.util.Xml
import android.view.ViewGroup
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.shared.CurrentRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.max

@Composable
fun ColumnToRowScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        CurrentRoute(navController = navController)
        Spacer(modifier = Modifier.height(20.dp))
//        aaa()
        //ColumnToRow()
//        AndroidView(
//            modifier = Modifier.weight(1f).background(color = Color.Cyan),
//            factory = {ctx ->
//                val attrs = ctx.resources.getXml(R.xml.maps)
//                MapView(ctx, attrs)
//            }
//        ){view ->
//
//        }
        Column(
            modifier = Modifier
                .width(100.dp)
                .height(300.dp)
                .background(color = Color.LightGray)
        ) {
            tt("123", "ETH")
            tt("123123123123123", "ETH")
        }
    }
}

@Composable
fun tt(v1: String, v2: String, modifier: Modifier = Modifier) {
    Layout(
        modifier = modifier,
        content = {
            Text(text = v1, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(text = v2, maxLines = 1)
        }
    ) { measurables, constraints ->
        val constraintsForLast = constraints.copy(
            minWidth = 0,
            minHeight = 0
        )
        val placeable1 = measurables[1].measure(constraintsForLast)
        val constraintsForFirst =
            constraintsForLast.copy(maxWidth = constraints.maxWidth - placeable1.width)
        val placeable0 = measurables[0].measure(constraintsForFirst)
        val maxHeight = max(max(placeable0.height, placeable1.height), constraints.minHeight)
        layout(width = constraints.maxWidth, height = maxHeight) {
            placeable0.place(0, 0)
            placeable1.place(placeable0.width, 0)
        }
    }
}

@Composable
fun aaa() {
    val i = remember {
        MutableInteractionSource()
    }
    var value by rememberSaveable {
        mutableStateOf("")
    }
    BasicTextField(
        value = value,
        onValueChange = { value = it },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        decorationBox = {
            TextFieldDefaults.TextFieldDecorationBox(
                value = value,
                innerTextField = {
                    Box(
                        modifier = Modifier.background(
                            color = Color.LightGray,
                            RoundedCornerShape(percent = 50)
                        )
                    ) {
                        Text(text = if (value.isEmpty()) "0000" else value)
                    }
                },
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = i
            )
        }
    )
}

@Composable
fun cont(
    content: @Composable LookaheadLayoutScope.() -> Unit
): @Composable LookaheadLayoutScope.() -> Unit {
    return remember {
        movableContentWithReceiverOf(content = content)
    }
}

@Composable
fun ColumnScope.ColumnToRow() {
    var isColumn by remember { mutableStateOf(true) }
    val content = cont {
        Box(modifier = Modifier
            .padding(10.dp)
            .width(
                if (isColumn) {
                    200.dp
                } else {
                    100.dp
                }
            )
            .height(100.dp)
            .background(color = Color.Green)
            .clickable {
                isColumn = !isColumn
            }
//            .animateTransformation(lookaheadScope = this)
            .animateMovement(lookaheadScope = this)
        )
        Box(modifier = Modifier
            .padding(10.dp)
            .width(
                if (isColumn) {
                    200.dp
                } else {
                    100.dp
                }
            )
            .height(100.dp)
            .background(color = Color.Blue)
            .clickable {
                isColumn = !isColumn
            }
//            .animateTransformation(lookaheadScope = this)
            .animateMovement(lookaheadScope = this)
        )
    }
    LookaheadLayout(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .weight(1f),
        content = {
            if (isColumn) {
                Column(modifier = Modifier.fillMaxSize()) {
                    content()
                }
            } else {
                Row(modifier = Modifier.fillMaxSize()) {
                    content()
                }
            }
        },
        measurePolicy = { measurables, constraints ->
            val placeables =
                measurables.map { measurable ->
                    measurable.measure(
                        constraints.copy(
                            minWidth = 0,
                            minHeight = 0
                        )
                    )
                }
            val maxWidth: Int = max(placeables.maxOf { it.width }, constraints.minWidth)
            val maxHeight = max(placeables.maxOf { it.height }, constraints.minHeight)
            layout(width = maxWidth, height = maxHeight) {
                placeables.forEachIndexed { index, placeable ->
                    placeable.place(x = 0, y = 0)
                }
            }
        })
}

fun Modifier.animateMovement(
    lookaheadScope: LookaheadLayoutScope,
    animationSpec: AnimationSpec<IntOffset> = defaultSpring()
) = composed {
    var placementOffset by remember { mutableStateOf(IntOffset.Zero) }
    val coroutineScope = rememberCoroutineScope()
    val offsetAnimation = remember {
        DeferredAnimation(coroutineScope, IntOffset.VectorConverter)
    }

    with(lookaheadScope) {
        this@composed
            .onPlaced { lookaheadScopeCoordinates, layoutCoordinates ->
                val targetOffset = lookaheadScopeCoordinates
                    .localLookaheadPositionOf(sourceCoordinates = layoutCoordinates)
                    .round()
                Log.e("AA", "move target updete $targetOffset")
                offsetAnimation.updateTarget(targetOffset, animationSpec)
                placementOffset = lookaheadScopeCoordinates
                    .localPositionOf(
                        sourceCoordinates = layoutCoordinates,
                        relativeToSource = Offset.Zero
                    )
                    .round()
            }
            .intermediateLayout { measurable, constraints, _ ->
                val placeable = measurable.measure(constraints)
                layout(placeable.width, placeable.height) {
                    val (x, y) = offsetAnimation.value?.let { it - placementOffset }
                        ?: (offsetAnimation.target!! - placementOffset)
                    Log.e("AA", "intermediateLayout x,y = $x $y")
                    placeable.place(x, y)
                }
            }
    }
}

fun Modifier.animateTransformation(
    lookaheadScope: LookaheadLayoutScope,
    animationSpec: AnimationSpec<IntSize> = defaultSpring(),
) = composed {
    val coroutineScope = rememberCoroutineScope()
    val sizeAnimation = remember {
        DeferredAnimation(coroutineScope, IntSize.VectorConverter)
    }

    with(lookaheadScope) {
        this@composed.intermediateLayout { measurable, _, lookaheadSize ->
            sizeAnimation.updateTarget(lookaheadSize, animationSpec)
            val (width, height) = sizeAnimation.value ?: lookaheadSize
            val animatedConstraints = Constraints.fixed(
                width = width.coerceAtLeast(0),
                height = height.coerceAtLeast(0)
            )

            val placeable = measurable.measure(animatedConstraints)
            Log.e("AA", "trans ${placeable.width} ${placeable.height}")
            layout(width = placeable.width, height = placeable.height) {
                placeable.place(x = 0, y = 0)
            }
        }
    }
}

private fun <T> defaultSpring() = spring<T>(
    dampingRatio = Spring.DampingRatioLowBouncy,
    stiffness = Spring.StiffnessLow,
)

class DeferredAnimation<T, V : AnimationVector>(
    coroutineScope: CoroutineScope,
    vectorConverter: TwoWayConverter<T, V>
) {
    val value: T?
        get() = animatable?.value ?: target
    var target: T? by mutableStateOf(null)
        private set
    private var animationSpec: AnimationSpec<T> = spring()
    private var animatable: Animatable<T, V>? = null

    init {
        coroutineScope.launch {
            snapshotFlow { target }.collect { target ->
                if (target != null && target != animatable?.targetValue) {
                    animatable?.run {
                        launch { animateTo(target, animationSpec) }
                    } ?: Animatable(target, vectorConverter).let {
                        animatable = it
                    }
                }
            }
        }
    }

    fun updateTarget(targetValue: T, animationSpec: AnimationSpec<T>) {
        target = targetValue
        this.animationSpec = animationSpec
    }
}

