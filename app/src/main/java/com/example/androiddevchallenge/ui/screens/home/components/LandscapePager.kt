package com.example.androiddevchallenge.ui.screens.home.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.drawerBackground
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@SuppressLint("RestrictedApi")
@ExperimentalPagerApi
@Composable
fun LandscapePager(state: PagerState, url: List<String>) {
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