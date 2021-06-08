package com.example.androiddevchallenge.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.androiddevchallenge.ui.components.TopBar
import com.example.androiddevchallenge.ui.screens.home.LandScapeImageState.Error
import com.example.androiddevchallenge.ui.screens.home.LandScapeImageState.Loading
import com.example.androiddevchallenge.ui.screens.home.LandScapeImageState.NetworkError
import com.example.androiddevchallenge.ui.screens.home.LandScapeImageState.Success
import com.example.androiddevchallenge.ui.screens.home.components.LandscapePager
import com.example.androiddevchallenge.ui.theme.drawerBackground
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun Home(openDrawer: () -> Unit) {

  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(drawerBackground)
      .verticalScroll(rememberScrollState())
  ) {
    TopBar(
      title = "Disney Hotstar",
      leadingButtonIcon = Filled.Menu,
      trailingButtonIcon = Filled.Search,
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
  when (val value = viewModel.landscapeMutableState.value) {
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
      LandscapePager(state = pagerState, url = value.urls)
    }
  }

}

@ExperimentalPagerApi
@Preview
@Composable
fun show() {
  Home(openDrawer = {})
}