@file:OptIn(ExperimentalLayoutApi::class)

package nz.co.jonker.breedview.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsIgnoringVisibility
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil3.compose.AsyncImage
import nz.co.jonker.design.components.TopAppBarComponent
import kotlin.math.absoluteValue

@Composable
fun BreedViewScreen(
    viewModel: BreedViewViewModel,
    onUpNav: () -> Unit
) {

    val state = viewModel.screenState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.systemBarsIgnoringVisibility,
        topBar = { TopAppBarComponent(state.value.breedName.capitalize(Locale.current), onUpNav) }
    ) { innerPadding ->

        when {
            state.value.loading -> {}
            state.value.error -> {}
            state.value.breedImages.isEmpty() -> {}
            else -> DogBreedImagePager(
                modifier = Modifier.padding(innerPadding),
                images = state.value.breedImages
            )
        }
    }
}

@Composable
fun DogBreedImagePager(
    modifier: Modifier,
    images: List<String>
) {
    val pagerState = rememberPagerState(pageCount = { images.size })

    Box(modifier.fillMaxSize()) {
        HorizontalPager(
            modifier = modifier.fillMaxSize(),
            state = pagerState,
            beyondViewportPageCount = 2,
        ) { page ->
            val breedImage = images[page]

            AsyncImage(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .graphicsLayer {
                            val pageOffset = (
                                    (pagerState.currentPage - page) + pagerState
                                        .currentPageOffsetFraction
                                    ).absoluteValue

                            alpha = lerp(
                                start = 0.5f,
                                stop = 1f,
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )
                        },
                model = breedImage,
                contentDescription = null,
            )
        }

        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(16.dp)
                )
            }
        }
    }
}
