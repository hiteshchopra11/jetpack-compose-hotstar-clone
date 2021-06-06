package com.example.androiddevchallenge.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.repository.ImagesRepo
import com.example.androiddevchallenge.repository.utils.SafeResult
import com.example.androiddevchallenge.ui.screens.LandScapeImageState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
  private val repo: ImagesRepo
) : ViewModel() {

  val landscapeMutableState = mutableStateOf<LandScapeImageState>(Loading)

  init{
    landscapeMutableState.value = Loading
    viewModelScope.launch {
      when (val response = repo.getLandscapeImages()) {
        is SafeResult.Success -> {
          val data = mutableListOf<String>()
          for(i in 1..response.data.size){
            data.add(response.data[i-1].landscape_url)
          }
          landscapeMutableState.value = LandScapeImageState.Success(data)
        }
        is SafeResult.Failure -> {
          landscapeMutableState.value = LandScapeImageState.Error(response.message)
        }
        is SafeResult.NetworkError -> {
          landscapeMutableState.value = LandScapeImageState.NetworkError
        }
      }
    }
  }
}

sealed class LandScapeImageState {
  object Loading : LandScapeImageState()
  data class Success(val urls: List<String>) : LandScapeImageState()
  data class Error(val message: String) : LandScapeImageState()
  object NetworkError : LandScapeImageState()
}