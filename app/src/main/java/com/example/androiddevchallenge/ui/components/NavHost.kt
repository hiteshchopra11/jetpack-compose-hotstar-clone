package com.example.androiddevchallenge.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.androiddevchallenge.ui.routes.DrawerScreensRoutes
import com.example.androiddevchallenge.ui.screens.home.Home
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.Job

@ExperimentalPagerApi
@Composable
fun NavHostComponent(navController: NavHostController, openDrawer: () -> Job) {
  NavHost(
    navController = navController,
    startDestination = DrawerScreensRoutes.Movies.route
  ) {
    composable(DrawerScreensRoutes.Downloads.route) {
      Home(
        openDrawer = {
          openDrawer()
        }
      )
    }
    composable(DrawerScreensRoutes.Watchlist.route) {
      Home(
        openDrawer = {
          openDrawer()
        }
      )
    }
    composable(DrawerScreensRoutes.Prizes.route) {
      Home(
        openDrawer = {
          openDrawer()
        }
      )
    }
  }
}