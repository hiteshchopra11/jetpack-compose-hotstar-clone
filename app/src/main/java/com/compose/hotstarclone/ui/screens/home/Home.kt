package com.compose.hotstarclone.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.compose.hotstarclone.R.font
import com.compose.hotstarclone.ui.routes.bottomNavigationScreens
import com.compose.hotstarclone.ui.screens.home.components.BottomNavigationBar
import com.compose.hotstarclone.ui.screens.home.components.HorizontalPagerData
import com.compose.hotstarclone.ui.screens.home.components.PaginatedImages
import com.compose.hotstarclone.ui.screens.home.components.TopAppToolbar
import com.compose.hotstarclone.ui.theme.drawerBackground
import com.compose.hotstarclone.ui.theme.drawerItemTitle
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun Home(
  openDrawer: () -> Unit,
  navController: NavController
) {
  Scaffold(modifier = Modifier
    .fillMaxSize()
    .background(drawerBackground), scaffoldState = rememberScaffoldState(),
    topBar = { TopAppToolbar(openDrawer) },
    content = { HomeScreenContent() },
    bottomBar = {
      BottomNavigationBar(
        screens = bottomNavigationScreens,
        navController = navController
      )
    })
}

@Composable
fun HomeScreenContent(viewModel: HomeVM = hiltViewModel()) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(drawerBackground)
      .verticalScroll(rememberScrollState())
  ) {
    HorizontalPagerData(viewModel = viewModel)
    ImageTitleText(text = "Latest and Trending")
    PaginatedImages(items = viewModel.getPaginatedImages("Popular"))
    ImageTitleText(text = "Sports")
    PaginatedImages(items = viewModel.getPaginatedImages("Sports"))
    ImageTitleText(text = "Popular in Movies")
    PaginatedImages(items = viewModel.getPaginatedImages("Movies"))
    ImageTitleText(text = "Popular in Cricket")
    PaginatedImages(items = viewModel.getPaginatedImages("Cricket"))
    ImageTitleText(text = "Popular in Mythology")
    PaginatedImages(items = viewModel.getPaginatedImages("Mythology"))
    Spacer(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 64.dp))
  }
}

@Composable
private fun ImageTitleText(text: String) {
  Text(
    text = text,
    modifier = Modifier.padding(16.dp, 12.dp),
    color = drawerItemTitle,
    fontFamily = FontFamily(
      Font(font.roboto_regular)
    ), fontSize = 20.sp
  )
}