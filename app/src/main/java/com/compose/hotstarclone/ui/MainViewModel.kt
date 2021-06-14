package com.compose.hotstarclone.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.compose.hotstarclone.ui.routes.AppRoutes

class MainViewModel : ViewModel() {

  private val _currentRoute = MutableLiveData<AppRoutes>(AppRoutes.BottomNavigationRoutes.Home)
  val currentRoute: LiveData<AppRoutes> = _currentRoute

  fun setCurrentScreen(routes: AppRoutes) {
    _currentRoute.value = routes
  }
}