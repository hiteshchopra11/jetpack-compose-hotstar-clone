package com.compose.hotstarclone.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.compose.hotstarclone.ui.routes.DrawerScreensRoutes
import com.compose.hotstarclone.ui.screens.home.Home
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.Job

@ExperimentalPagerApi
@Composable
fun NavigationHost(navController: NavHostController, openDrawer: () -> Job) {
  NavHost(
    navController = navController,
    startDestination = DrawerScreensRoutes.Movies.route
  ) {
    composable(DrawerScreensRoutes.Downloads.route) {
      Home(
        openDrawer = {
          openDrawer()
        }, navController = navController
      )
    }
    composable(DrawerScreensRoutes.Watchlist.route) {
      Home(
        openDrawer = {
          openDrawer()
        }, navController = navController
      )
    }
    composable(DrawerScreensRoutes.Prizes.route) {
      Home(
        openDrawer = {
          openDrawer()
        }, navController = navController
      )
    }
  }
}