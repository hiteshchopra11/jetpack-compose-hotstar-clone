package com.compose.hotstarclone.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.compose.hotstarclone.ui.routes.AppRoutes.BottomNavigationRoutes
import com.compose.hotstarclone.ui.theme.drawerBackground

@Composable
fun BottomNavigationBar(
  modifier: Modifier = Modifier,
  screens: List<BottomNavigationRoutes>,
  navController: NavController
) {
  val selectedItem = remember { mutableStateOf(0) }
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val currentRoute = navBackStackEntry?.destination?.route
  BottomNavigation(modifier = modifier, backgroundColor = drawerBackground, elevation = 5.dp) {
    screens.forEachIndexed { index, screen ->
      BottomNavigationItem(
        selected = index == selectedItem.value,
        modifier = Modifier.align(Alignment.CenterVertically),
        icon = {
          Icon(
            painterResource(id = screen.icon!!),
            modifier = Modifier.align(Alignment.CenterVertically),
            contentDescription = screen.title
          )
        },
        label = { Text(text = screen.title) },
        selectedContentColor = Color.White,
        unselectedContentColor = Color.White.copy(0.4f),
        alwaysShowLabel = index != 2,
        onClick = {
          selectedItem.value = index
          navController.navigate(screen.route) {
            navController.graph.startDestinationRoute?.let { route ->
              popUpTo(route) {
                saveState = true
              }
            }
            launchSingleTop = true
            restoreState = true
          }
        }
      )
    }
  }
}