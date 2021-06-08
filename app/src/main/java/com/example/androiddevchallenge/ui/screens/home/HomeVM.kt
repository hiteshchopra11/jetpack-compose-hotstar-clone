package com.example.androiddevchallenge.ui.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.repository.ImagesRepo
import com.example.androiddevchallenge.data.repository.utils.SafeResult
import com.example.androiddevchallenge.ui.screens.home.ImageState.Error
import com.example.androiddevchallenge.ui.screens.home.ImageState.Loading
import com.example.androiddevchallenge.ui.screens.home.ImageState.NetworkError
import com.example.androiddevchallenge.ui.screens.home.ImageState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
  private val repo: ImagesRepo
) : ViewModel() {

  val landscapeMutableState = mutableStateOf<ImageState>(Loading)
  val trendingMutableState = mutableStateOf<ImageState>(Loading)

  init {
    landscapeMutableState.value = Loading
    trendingMutableState.value=Loading
    viewModelScope.launch {
      when (val response = repo.fetchImages("movies", "landscape", 1, 10)) {
        is SafeResult.Success -> {
          val data = mutableListOf<String>()
          for (element in response.data.photos) {
            data.add(element.src.landscape)
          }
          landscapeMutableState.value = Success(data)
        }
        is SafeResult.Failure -> {
          landscapeMutableState.value = Error(response.message)
        }
        is SafeResult.NetworkError -> {
          landscapeMutableState.value = NetworkError
        }
      }
      when (val response = repo.fetchImages("movies", "portrait", 1, 10)) {
        is SafeResult.Success -> {
          val data = mutableListOf<String>()
          for (element in response.data.photos) {
            data.add(element.src.portrait)
          }
          trendingMutableState.value = Success(data)
        }
        is SafeResult.Failure -> {
          trendingMutableState.value = Error(response.message)
        }
        is SafeResult.NetworkError -> {
          trendingMutableState.value = NetworkError
        }
      }
    }
  }
}

sealed class ImageState {
  object Loading : ImageState()
  data class Success(val urls: List<String>) : ImageState()
  data class Error(val message: String) : ImageState()
  object NetworkError : ImageState()
}