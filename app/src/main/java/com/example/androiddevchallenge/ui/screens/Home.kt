package com.example.androiddevchallenge.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.TopBar
import com.example.androiddevchallenge.ui.screens.LandScapeImageState.Error
import com.example.androiddevchallenge.ui.screens.LandScapeImageState.Loading
import com.example.androiddevchallenge.ui.screens.LandScapeImageState.NetworkError
import com.example.androiddevchallenge.ui.screens.LandScapeImageState.Success
import com.example.androiddevchallenge.ui.theme.drawerBackground
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.google.android.material.animation.AnimationUtils.lerp
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun Home(openDrawer: () -> Unit) {

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(drawerBackground)
  ) {
    TopBar(
      title = "Disney Hotstar",
      leadingButtonIcon = Icons.Filled.Menu,
      trailingButtonIcon = Icons.Filled.Search,
      onButtonClicked = { openDrawer() }
    )
    HorizontalPagerWithOffsetTransition()
  }
}

@SuppressLint("RestrictedApi")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerWithOffsetTransition(viewModel: HomeVM = hiltViewModel()) {
  val pagerState = rememberPagerState(
    pageCount = 6,
    // We increase the offscreen limit, to allow pre-loading of images
    initialOffscreenLimit = 2,
  )
  val response = viewModel.landscapeMutableState.value
  Pager(state = pagerState, value = response)
}

@SuppressLint("RestrictedApi")
@ExperimentalPagerApi
@Composable
fun Pager(state: PagerState, value: LandScapeImageState) {
  when (value) {
    is Error -> {
      Column(
        Modifier
          .height(200.dp)
          .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(text = value.message, color = Color.White)
      }
    }
    Loading -> {
      Column(
        Modifier
          .height(200.dp)
          .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        CircularProgressIndicator(
          color = Color.White
        )
      }
    }
    NetworkError -> {
      Column(
        Modifier
          .height(200.dp)
          .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(text = "Network Error Occurred", color = Color.White)
      }
    }
    is Success -> {
      Landscape(state = state, url = value.urls)
    }
  }

}

@SuppressLint("RestrictedApi")
@ExperimentalPagerApi
@Composable
private fun Landscape(state: PagerState, url: List<String>) {
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
        .padding(4.dp)
        .fillMaxWidth(0.95f)
        .aspectRatio(1f)
    ) {
      Box {
        Image(
          painter = rememberCoilPainter(url[page]),
          contentDescription = null,
          modifier = Modifier.fillMaxSize(),
          contentScale = ContentScale.Crop
        )
      }
    }
  }
}

@ExperimentalPagerApi
@Preview
@Composable
fun show() {
  Home(openDrawer = {})
}