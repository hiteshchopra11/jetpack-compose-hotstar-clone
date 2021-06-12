package com.compose.hotstarclone.ui.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.compose.hotstarclone.data.SafeResult
import com.compose.hotstarclone.data.model.ImageParamsRequest
import com.compose.hotstarclone.data.remote.model.Photo
import com.compose.hotstarclone.domain.usecase.GetLandscapeImagesUseCase
import com.compose.hotstarclone.domain.usecase.GetPaginatedImagesUseCase
import com.compose.hotstarclone.ui.screens.home.ImageState.Error
import com.compose.hotstarclone.ui.screens.home.ImageState.Loading
import com.compose.hotstarclone.ui.screens.home.ImageState.NetworkError
import com.compose.hotstarclone.ui.screens.home.ImageState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
  private val getPaginatedImagesUseCase: GetPaginatedImagesUseCase,
  private val getLandScapeImagesUseCase: GetLandscapeImagesUseCase
) : ViewModel() {

  val landscapeMutableState = mutableStateOf<ImageState>(Loading)

  fun getPaginatedImages(query: String): Flow<PagingData<Photo>> {
    return getPaginatedImagesUseCase.perform(ImageParamsRequest(query, "portrait"))
  }

  fun getLandscapeImages() {
    viewModelScope.launch {
      when (val response = getLandScapeImagesUseCase.performAsync()) {
        is SafeResult.Success -> {
          val data = mutableListOf<String>()
          for (element in response.data) {
            data.add(element.landscape_url)
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
    }
  }
}

sealed class ImageState {
  object Loading : ImageState()
  data class Success(val urls: List<String>) : ImageState()
  data class Error(val message: String) : ImageState()
  object NetworkError : ImageState()
}