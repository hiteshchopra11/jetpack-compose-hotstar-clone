package com.example.androiddevchallenge

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.Drawer
import com.example.androiddevchallenge.ui.DrawerScreens
import com.example.androiddevchallenge.ui.screens.Home
import com.example.androiddevchallenge.ui.theme.MyTheme
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val context: Context = applicationContext
    setContent {
      MyTheme {
        MyScreenContent(context)
      }
    }
  }
}

@Composable
fun MyScreenContent(context: Context) {
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
        }
      ) {
        NavHost(
          navController = navController,
          startDestination = DrawerScreens.Movies.route
        ) {
          composable(DrawerScreens.Downloads.route) {
            Home(
              openDrawer = {
                openDrawer()
              }
            )
          }
          composable(DrawerScreens.Watchlist.route) {
            Home(
              openDrawer = {
                openDrawer()
              }
            )
          }
          composable(DrawerScreens.Prizes.route) {
            Home(
              openDrawer = {
                openDrawer()
              }
            )
          }
        }
      }
    }
  }
}

@Preview
@Composable
fun Show() {
  MyTheme {
    Column(modifier = Modifier.fillMaxHeight()) {
      MyScreenContent(context = LocalContext.current)
    }
  }
}