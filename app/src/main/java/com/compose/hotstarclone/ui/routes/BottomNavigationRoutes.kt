package com.compose.hotstarclone.ui.routes

import com.compose.hotstarclone.R

sealed class BottomNavigationRoutes(
  val title: String,
  val icon: Int,
  val route: String,
) {
  object Home : BottomNavigationRoutes("Home", R.drawable.ic_home, route = "Home")
  object Tv : BottomNavigationRoutes("Tv", R.drawable.ic_tv, route = "Home")
  object Hotstar : BottomNavigationRoutes("", R.drawable.ic_logo, route = "Home")
  object Sports : BottomNavigationRoutes("Sports", R.drawable.ic_sports, route = "Home")
  object News : BottomNavigationRoutes("News", R.drawable.ic_news, route = "Home")
}

val bottomNavigationScreens = listOf(
  BottomNavigationRoutes.Home,
  BottomNavigationRoutes.Tv,
  BottomNavigationRoutes.Hotstar,
  BottomNavigationRoutes.Sports,
  BottomNavigationRoutes.News,
)