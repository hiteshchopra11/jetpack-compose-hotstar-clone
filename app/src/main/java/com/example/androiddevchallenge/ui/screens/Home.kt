package com.example.androiddevchallenge.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Colors
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign.Center
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.TopBar
import com.example.androiddevchallenge.ui.theme.drawerBackground
import com.example.androiddevchallenge.ui.theme.purple500
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import com.google.android.material.animation.AnimationUtils.lerp
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun Home(openDrawer: () -> Unit) {
  Column(modifier = Modifier.fillMaxSize()) {
    TopBar(
      title = "Home",
      leadingButtonIcon = Icons.Filled.Menu,
      trailingButtonIcon = Icons.Filled.Search,
      onButtonClicked = { openDrawer() }
    )
//    Column(
//      modifier = Modifier.fillMaxSize(),
//      verticalArrangement = Arrangement.Center,
//      horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//    }
    LazyColumnDemo()
  }
}

@SuppressLint("RestrictedApi")
@ExperimentalPagerApi
@Composable
fun LazyColumnDemo() {
  val pagerState = rememberPagerState(pageCount = 5)
  HorizontalPager(
    state = pagerState,
    verticalAlignment = Alignment.CenterVertically,
    horizontalAlignment = Alignment.CenterHorizontally
  ) { page ->
    val color = if(page%2==0) drawerBackground else purple500
    Card(
      Modifier
        .fillMaxWidth()
        .graphicsLayer {
          val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
          lerp(
            0.85f,
            1f,
            1f - pageOffset.coerceIn(0f, 1f)
          ).also { scale ->
            scaleX = scale
            scaleY = scale
          }

          alpha = lerp(
            0.5f, 1f, 1f - pageOffset.coerceIn(0f, 1f)
          )
        }
    ) {
      Text(
        "Hello World",
        textAlign = Center,
        modifier = Modifier
          .background(color)
          .fillMaxHeight()
      )
    }
  }
}

@ExperimentalPagerApi
@Preview
@Composable
fun show() {
  Home(openDrawer = {})
}