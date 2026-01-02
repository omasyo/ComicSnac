package com.omasyo.comicsnac.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.max

interface CarouselScope {
    val interactionSource: InteractionSource
}

private class CarouselScopeImpl : CarouselScope {
    override val interactionSource = MutableInteractionSource()
}

@Composable
fun <T> Carousel(
    items: List<T>,
    onItemClick: (T) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable CarouselScope.(T) -> Unit,
) {

    val lazyListState =
        rememberLazyListState(items.size * (Int.MAX_VALUE / (2 * items.size)) - 1)

    val coroutineScope = rememberCoroutineScope()

    LazyRow(
        state = lazyListState,
        flingBehavior = rememberSnapFlingBehavior(lazyListState, SnapPosition.Center),
        horizontalArrangement = Arrangement.spacedBy(
            0f.dp,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        items(Int.MAX_VALUE) { index ->
            val visibleIndex = index % items.size
            val item = items[visibleIndex]

            val scope = remember { CarouselScopeImpl() }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillParentMaxWidth(.333f) // TODO make .2 on some occassions
                    .graphicsLayer {
                        val center = lazyListState.layoutInfo.viewportEndOffset / 2f
                        val itemInfo = lazyListState.layoutInfo.visibleItemsInfo
                            .find { it.index == index }

                        if (itemInfo != null) {
                            val itemCenter = itemInfo.offset + (itemInfo.size / 2f)
                            val distanceFromCenter = abs(center - itemCenter)

                            val fraction = distanceFromCenter / center

                            val scale = max(lerp(1.1f, 0.6f, fraction), 0.5f)

                            scaleX = scale
                            scaleY = scale
                        }
                    }
                    .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
                    .drawWithContent {
                        drawContent()


                        val center = lazyListState.layoutInfo.viewportEndOffset / 2f
                        val itemInfo = lazyListState.layoutInfo.visibleItemsInfo
                            .find { it.index == index }

                        if (itemInfo != null) {
                            val itemCenter = itemInfo.offset + (itemInfo.size / 2f)
                            val distanceFromCenter = abs(center - itemCenter)

                            val fraction = distanceFromCenter / center

                            drawRect(
                                color = Color.Black.copy(
                                    alpha = lerp(
                                        0f,
                                        0.3f,
                                        fraction
                                    )
                                ), // Adjust 0.5f to control darkness
                                blendMode = BlendMode.SrcAtop
                            )
                        }
                    }
                    .clickable(
                        indication = null,
                        interactionSource = scope.interactionSource,
                    ) {
                        val currentIndex = lazyListState.firstVisibleItemIndex + 1
                        if (index == currentIndex) {
                            onItemClick(item)
                        } else {
                            coroutineScope.launch { lazyListState.animateScrollToItem(index - 1) }
                        }
                    }
                    .padding(top = 24f.dp, bottom = 16f.dp)
            ) {
                scope.content(item)
            }
        }
    }
}