package com.compose.hotstarclone.ui.routes

import com.compose.hotstarclone.R

sealed class AppRoutes(
  val route: String,
  val title: String,
  val subtitle: String? = null,
  val icon: Int?
) {

  sealed class DrawerScreensRoutes(
    title: String,
    icon: Int,
    subtitle: String? = null,
    route: String,
  ) : AppRoutes(route, title, subtitle, icon) {
    object Downloads : DrawerScreensRoutes(
      "Downloads", R.drawable.ic_download, "Watch videos offline",
      route = "NavigationDrawer"
    )

    object Watchlist : DrawerScreensRoutes(
      "Watchlist", R.drawable.ic_watchlist, "Save to watch later",
      route = "NavigationDrawer"
    )

    object Prizes : DrawerScreensRoutes(
      "Prizes", R.drawable.ic_prizes, "Prizes you have won",
      route = "NavigationDrawer"
    )

    object Movies : DrawerScreensRoutes("Movies", R.drawable.ic_movies, route = "NavigationDrawer")
    object Premium : DrawerScreensRoutes("Premium", R.drawable.ic_premium, route = "NavigationDrawer")
    object Trending : DrawerScreensRoutes("Trending", R.drawable.ic_trending, route = "NavigationDrawer")
    object Channels : DrawerScreensRoutes("Channels", R.drawable.ic_channels, route = "NavigationDrawer")
    object Languages : DrawerScreensRoutes("Languages", R.drawable.ic_langauges, route = "NavigationDrawer")
    object Genres : DrawerScreensRoutes("Genres", R.drawable.ic_genres, route = "NavigationDrawer")
  }

  sealed class BottomNavigationRoutes(
    title: String,
    icon: Int,
    route: String,
  ) : AppRoutes(route = route, title = title, icon = icon) {
    object Home : BottomNavigationRoutes("Home", R.drawable.ic_home, route = "Home")
    object Tv : BottomNavigationRoutes("Tv", R.drawable.ic_tv, route = "Tv")
    object Disney : BottomNavigationRoutes("", R.drawable.ic_logo, route = "Disney")
    object Sports : BottomNavigationRoutes("Sports", R.drawable.ic_sports, route = "Sports")
    object News : BottomNavigationRoutes("News", R.drawable.ic_news, route = "News")
  }
}

val drawerScreens = listOf(
  AppRoutes.DrawerScreensRoutes.Downloads,
  AppRoutes.DrawerScreensRoutes.Watchlist,
  AppRoutes.DrawerScreensRoutes.Prizes,
  AppRoutes.DrawerScreensRoutes.Movies,
  AppRoutes.DrawerScreensRoutes.Premium,
  AppRoutes.DrawerScreensRoutes.Trending,
  AppRoutes.DrawerScreensRoutes.Channels,
  AppRoutes.DrawerScreensRoutes.Languages,
  AppRoutes.DrawerScreensRoutes.Genres,
)

val bottomNavScreens = listOf(
  AppRoutes.BottomNavigationRoutes.Home,
  AppRoutes.BottomNavigationRoutes.Tv,
  AppRoutes.BottomNavigationRoutes.Disney,
  AppRoutes.BottomNavigationRoutes.Sports,
  AppRoutes.BottomNavigationRoutes.News,
)
