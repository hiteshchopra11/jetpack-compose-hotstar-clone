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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.compose.hotstarclone.ui.components.Drawer
import com.compose.hotstarclone.ui.components.NavHostComponent
import com.compose.hotstarclone.ui.theme.MyTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
  @ExperimentalPagerApi
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MyTheme {
        MyScreenContent()
      }
    }
  }
}

@ExperimentalPagerApi
@Composable
fun MyScreenContent() {
  Column(modifier = Modifier.fillMaxHeight()) {
    val navController = rememberNavController()
    Surface(color = MaterialTheme.colors.background) {
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
          Drawer(
            onDestinationClicked = {
              scope.launch {
                drawerState.close()
              }
            }
          )
        }) {
        NavHostComponent(navController = navController, openDrawer = openDrawer)
      }
    }
  }
}

@ExperimentalPagerApi
@Preview
@Composable
fun Show() {
  MyTheme {
    Column(modifier = Modifier.fillMaxHeight()) {
      MyScreenContent()
    }
  }
}