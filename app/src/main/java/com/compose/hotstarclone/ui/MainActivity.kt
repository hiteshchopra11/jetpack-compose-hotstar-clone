package com.compose.hotstarclone.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.hotstarclone.ui.components.NavigationDrawer
import com.compose.hotstarclone.ui.components.BottomNavigationBar
import com.compose.hotstarclone.ui.components.NavigationHostT
import com.compose.hotstarclone.ui.components.TopAppToolbar
import com.compose.hotstarclone.ui.routes.AppRoutes
import com.compose.hotstarclone.ui.routes.AppRoutes.BottomNavigationRoutes
import com.compose.hotstarclone.ui.routes.AppRoutes.DrawerScreensRoutes
import com.compose.hotstarclone.ui.routes.bottomNavScreens
import com.compose.hotstarclone.ui.screens.download.Download
import com.compose.hotstarclone.ui.screens.home.Home
import com.compose.hotstarclone.ui.theme.HotstarCloneTheme
import com.compose.hotstarclone.ui.theme.drawerBackground
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      HotstarCloneTheme {
        AppContent()
      }
    }
  }
}

@ExperimentalPagerApi
@Composable
fun AppContent(viewModel: MainViewModel = hiltViewModel()) {
  val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
  val currentScreen = viewModel.currentRoute.observeAsState()
  val navController = rememberNavController()
  val scope = rememberCoroutineScope()
  val openDrawer = {
    scope.launch {
      scaffoldState.drawerState.open()
    }
  }

  Scaffold(
    modifier = Modifier
      .background(drawerBackground), scaffoldState = rememberScaffoldState(),
    topBar = { TopAppToolbar(openDrawer) },
    drawerContent = {
      NavigationDrawer(
        modifier = Modifier
          .background(drawerBackground)
          .fillMaxSize()
          .padding(top = 30.dp),
        onDestinationClicked = {
          scope.launch { }
        })
    },
    bottomBar = {
      BottomNavigationBar(
        screens = bottomNavScreens,
        navController = navController
      )
    }
  ) {
    NavigationHost(navController = navController)
  }
}

@ExperimentalPagerApi
@Composable
fun NavigationHost(navController: NavController) {
  NavHost(
    navController = navController as NavHostController,
    startDestination = BottomNavigationRoutes.Home.route
  ) {
    composable(BottomNavigationRoutes.Home.route) { Home() }
    composable(DrawerScreensRoutes.Downloads.route) { Download() }
    composable(BottomNavigationRoutes.Tv.route) { Download() }
  }
}