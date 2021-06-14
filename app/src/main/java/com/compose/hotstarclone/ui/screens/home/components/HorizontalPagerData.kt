package com.compose.hotstarclone.ui.screens.home.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.compose.hotstarclone.ui.components.DisplayError
import com.compose.hotstarclone.ui.components.LoadingItem
import com.compose.hotstarclone.ui.components.LoadingView
import com.compose.hotstarclone.ui.screens.home.HomeVM
import com.compose.hotstarclone.ui.screens.home.ImageState.Loading
import com.compose.hotstarclone.ui.screens.home.ImageState.NetworkError
import com.compose.hotstarclone.ui.screens.home.ImageState.Success
import com.compose.hotstarclone.ui.theme.drawerBackground
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.google.android.material.animation.AnimationUtils.lerp
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@SuppressLint("RestrictedApi")
@Composable
fun HorizontalPagerData(viewModel: HomeVM) {
  LaunchedEffect(key1 = true) {
    viewModel.getLandscapeImages()
  }
  val pagerState = rememberPagerState(
    pageCount = 6,
    initialOffscreenLimit = 2,
  )
  when (val value = viewModel.landscapeMutableState.value) {
    is Error -> {
      DisplayError(error = value)
    }
    Loading -> {
      LoadingView(
        modifier = Modifier
          .height(200.dp)
          .fillMaxWidth(),
      )
    }
    NetworkError -> {
      DisplayError(message = "Network Error Occurred")
    }
    is Success -> {
      LandscapePager(state = pagerState, url = value.urls)
    }
  }
}

@SuppressLint("RestrictedApi")
@ExperimentalPagerApi
@Composable
private fun LandscapePager(state: PagerState, url: List<String>) {
  HorizontalPager(
    state = state,
    modifier = Modifier
      .fillMaxWidth()
      .padding(0.dp, 8.dp, 0.dp, 0.dp)
      .height(200.dp)
  ) { page ->
    Card(
      modifier = Modifier
        .height(200.dp)
        .background(drawerBackground)
        .padding(2.dp)
        .graphicsLayer {
          val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
          lerp(
            0.95f,
            1f,
            1f - pageOffset.coerceIn(0f, 1f)
          ).also { scale ->
            scaleX = scale
            scaleY = scale
          }
          alpha = lerp(
            0.5f,
            1f,
            1f - pageOffset.coerceIn(0f, 1f)
          )
        }
        .fillMaxWidth(0.90f)
        .aspectRatio(1f)
    ) {
      Image(
        painter = rememberCoilPainter(url[page]),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
      )
    }
  }
}
