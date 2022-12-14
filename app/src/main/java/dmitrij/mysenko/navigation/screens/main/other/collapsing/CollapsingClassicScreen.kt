package dmitrij.mysenko.navigation.screens.main.other.collapsing

import android.util.Log
import androidx.compose.animation.core.FloatDecayAnimationSpec
import androidx.compose.animation.core.FloatExponentialDecaySpec
import androidx.compose.animation.core.animateDecay
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dmitrij.mysenko.navigation.R
import dmitrij.mysenko.navigation.screens.Screen
import dmitrij.mysenko.navigation.shared.CurrentRoute
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

@Composable
fun CollapsingClassicScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        CurrentRoute(navController = navController)
        Spacer(modifier = Modifier.height(20.dp))

        val data = mutableListOf<ListItem>()
        repeat(20) {
            data.add(ListItem(it, "item_$it"))
        }

        CollapsingClassic(
            data = data.toList(),
            minHeight = 80.dp,
            maxHeight = 300.dp,
            background = Color((180 shl 24) or 255),
            holderContent = { item ->
                Text(
                    text = item.text,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .background(color = Color((150 shl 24) or (255 shl 16) or (255 shl 8) or 255))
                        .padding(10.dp)
                )
            },
            collapsingContent = { state ->
                BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.obj18),
                        contentDescription = "Saturn",
                        modifier = Modifier
                            .padding(
                                top = 50.dp
                            )
                            .size(150.dp)
                            .graphicsLayer {
                                translationX = ((minWidth - 100.dp) * state.progress).toPx()
                                translationY = -100.dp.toPx() * state.progress
                                scaleX = 1f + state.progress
                                scaleY = 1f + state.progress
                            }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.obj16),
                        contentDescription = "Moon",
                        modifier = Modifier
                            .padding(top = 500.dp)
                            .size(80.dp)
                            .align(alignment = Alignment.TopEnd)
                            .graphicsLayer {
                                translationY = -(250.dp * (1-state.progress)).toPx()
                                translationX = -((minWidth - 100.dp) * (1-state.progress)).toPx()
                            }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.obj17),
                        contentDescription = "Moon",
                        modifier = Modifier
                            .padding(top = 250.dp, end = 10.dp)
                            .size(100.dp)
                            .align(alignment = Alignment.TopEnd)
                            .graphicsLayer {
                                translationX = 50.dp.toPx() * (1 - state.progress)
                            }
                    )
                    Image(
                        painter = painterResource(id = R.drawable.obj11),
                        contentDescription = "Earth",
                        modifier = Modifier
                            .padding(top = maxHeight / 2)
                            .size(maxWidth * 0.7f)
                            .graphicsLayer {
                                translationX = 50.dp.toPx() - 200.dp.toPx() * (1 - state.progress)
                            }
                    )
                }

                val density = LocalDensity.current.density
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((state.height / density).dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.obj3),
                        contentDescription = "Image",
                        modifier = Modifier
                            .padding(
                                top = 15.dp + 35.dp * state.progress,
                                start = 20.dp + (minWidth - 190.dp) * state.progress / 2
                            )
                            .size(50.dp + 100.dp * state.progress)
                    )
                    Text(
                        text = "Black Hole",
                        fontSize = (20 + 20 * state.progress).sp,
                        fontWeight = FontWeight.W700,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth(0.8f + state.progress)
                            .padding(top = 220.dp * state.progress)
                            .align(Alignment.Center)
                    )
                }
            }
        )
    }
}

@Composable
fun CollapsingClassic(
    data: List<ListItem>,
    minHeight: Dp = 50.dp,
    maxHeight: Dp = 200.dp,
    background: Color,
    holderContent: @Composable (ListItem) -> Unit,
    collapsingContent: @Composable (CollapsingState) -> Unit
) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val stateCollapsing = rememberCollapsingState(min = minHeight, max = maxHeight)

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                stateCollapsing.isTop =
                    listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0
                stateCollapsing.scrollOffset = stateCollapsing.scrollOffset - available.y
                return Offset(0f, stateCollapsing.consumed)
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                if (available.y > 0) {
                    scope.launch {
                        animateDecay(
                            initialValue = stateCollapsing.height,
                            initialVelocity = available.y,
                            animationSpec = FloatExponentialDecaySpec()
                        ) { value, velocity ->
                            stateCollapsing.isTop =
                                listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0
                            stateCollapsing.scrollOffset =
                                stateCollapsing.scrollOffset - (value - stateCollapsing.height)
                            if (stateCollapsing.scrollOffset == 0f) {
                                scope.coroutineContext.cancelChildren()
                            }
                        }
                    }
                }
                return super.onPostFling(consumed, available)
            }
        }
    }

    Box(
        modifier = Modifier
            .nestedScroll(nestedScrollConnection)
            .background(color = background)
    ) {

        collapsingContent.invoke(stateCollapsing)

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight()
                .align(alignment = Alignment.Center)
                .graphicsLayer {
                    translationY = stateCollapsing.height
                }
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = { scope.coroutineContext.cancelChildren() }
                    )
                },
            state = listState,
            contentPadding = PaddingValues(bottom = if (stateCollapsing.progress > 0) maxHeight else minHeight)
        ) {
            items(items = data, key = { it.id }) { item ->
                holderContent.invoke(item)
            }
        }
    }
}

@Composable
private fun rememberCollapsingState(min: Dp, max: Dp): CollapsingState {
    val density = LocalDensity.current.density
    return rememberSaveable(saver = CollapsingStateImpl.Saver) {
        CollapsingStateImpl(min = min.value * density, max = max.value * density)
    }
}

class CollapsingStateImpl(
    private val min: Float,
    private val max: Float,
    scrollOffset: Float = 0f
) :
    CollapsingState {

    init {
        require(min in 0f..max) {
            "Wrong min and max height"
        }
    }

    private val delta = max - min
    private var _scrollOffset by mutableStateOf(
        value = scrollOffset.coerceIn(0f, delta),
        policy = structuralEqualityPolicy()
    )
    private var _consumed: Float = 0f

    override val height: Float
        get() = (max - scrollOffset).coerceIn(min, max)

    override var scrollOffset: Float
        get() = _scrollOffset
        set(value) {
            if (isTop) {
                val oldOffset = _scrollOffset
                _scrollOffset = value.coerceIn(0f, delta)
                _consumed = oldOffset - _scrollOffset
            } else {
                _consumed = 0f
            }
        }

    override val consumed: Float
        get() = _consumed

    override val progress: Float
        get() = 1 - (max - height) / delta

    override var isTop: Boolean = true

    companion object {
        val Saver = run {

            val minHeightKey = "MinHeight"
            val maxHeightKey = "MaxHeight"
            val scrollOffsetKey = "ScrollOffset"

            mapSaver(
                save = {
                    mapOf(
                        minHeightKey to it.min,
                        maxHeightKey to it.max,
                        scrollOffsetKey to it.scrollOffset
                    )
                },
                restore = {
                    CollapsingStateImpl(
                        min = it[minHeightKey] as Float,
                        max = it[maxHeightKey] as Float,
                        scrollOffset = it[scrollOffsetKey] as Float,
                    )
                }
            )
        }
    }
}

@Stable
interface CollapsingState {
    val height: Float
    var isTop: Boolean
    val progress: Float
    var scrollOffset: Float
    val consumed: Float
}