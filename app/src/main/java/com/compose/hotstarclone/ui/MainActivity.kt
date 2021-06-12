package com.compose.hotstarclone.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.compose.hotstarclone.ui.components.NavigationDrawer
import com.compose.hotstarclone.ui.components.NavigationHost
import com.compose.hotstarclone.ui.theme.HotstarCloneTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  @ExperimentalPagerApi
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
fun AppContent() {
  Column(modifier = Modifier.fillMaxHeight()) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openDrawer = {
      scope.launch {
        drawerState.open()
      }
    }
    ModalDrawer(
      drawerState = drawerState,
      gesturesEnabled = drawerState.isOpen,
      drawerContent = {
        NavigationDrawer(
          onDestinationClicked = {
            scope.launch {
              drawerState.close()
            }
          }
        )
      }) {
      NavigationHost(navController = navController, openDrawer = openDrawer)
    }
  }
}