package com.example.androiddevchallenge.ui.screens.home

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R.font
import com.example.androiddevchallenge.ui.components.TopBar
import com.example.androiddevchallenge.ui.screens.home.ImageState.Loading
import com.example.androiddevchallenge.ui.screens.home.ImageState.NetworkError
import com.example.androiddevchallenge.ui.screens.home.ImageState.Success
import com.example.androiddevchallenge.ui.screens.home.components.LandscapePager
import com.example.androiddevchallenge.ui.theme.drawerBackground
import com.example.androiddevchallenge.ui.theme.drawerItemTitle
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun Home(openDrawer: () -> Unit, viewModel: HomeVM = hiltViewModel()) {

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
    Text(
      text = "Latest and Trending",
      modifier = Modifier.padding(12.dp),
      color = drawerItemTitle,
      fontFamily = FontFamily(
        Font(font.istok_bold)
      ), fontSize = 20.sp
    )
    val list = arrayListOf<String>()
    when (val state = viewModel.trendingMutableState.value) {
      is ImageState.Error -> TODO()
      Loading -> {

      }
      NetworkError -> {

      }
      is Success -> {
        list.addAll(state.urls)
        LazyRow(modifier = Modifier
          .padding(12.dp)
          .height(300.dp)) {
          items(
            list.size,
            itemContent = { item ->
              Card(
                modifier = Modifier
                  .height(250.dp)
                  .background(drawerBackground)
                  .padding(4.dp)
                  .width(250.dp)
                  .aspectRatio(1f)
              ) {
                  Image(
                    painter = rememberCoilPainter(list[item]),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                  )
              }
            }

          )
        }
      }
    }
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
        value.message?.let { Text(text = it, color = Color.White) }
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