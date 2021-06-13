package com.compose.hotstarclone.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.compose.hotstarclone.ui.routes.AppRoutes.BottomNavigationRoutes
import com.compose.hotstarclone.ui.routes.AppRoutes.DrawerScreensRoutes
import com.compose.hotstarclone.ui.routes.drawerScreens
import com.compose.hotstarclone.ui.screens.download.Download
import com.compose.hotstarclone.ui.screens.home.Home
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.Job

@ExperimentalPagerApi
@Composable
fun NavigationHostT(navController: NavHostController, openDrawer: () -> Job) {
  NavHost(
    navController = navController,
    startDestination = BottomNavigationRoutes.Home.route
  ) {
    drawerScreens.forEach { screen ->
      composable(screen.route) {
        Home()
      }
    }
  }
}