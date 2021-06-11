package com.compose.hotstarclone.ui.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.compose.hotstarclone.data.model.ImageParamsRequest
import com.compose.hotstarclone.data.remote.model.Photo

import com.compose.hotstarclone.domain.usecase.GetPaginatedImagesUseCase
import com.compose.hotstarclone.ui.screens.home.ImageState.Error
import com.compose.hotstarclone.ui.screens.home.ImageState.Loading
import com.compose.hotstarclone.ui.screens.home.ImageState.NetworkError
import com.compose.hotstarclone.ui.screens.home.ImageState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
  private val getPaginatedImagesUseCase: GetPaginatedImagesUseCase
) : ViewModel() {

  val landscapeMutableState = mutableStateOf<ImageState>(Loading)
  val trendingMutableState = mutableStateOf<ImageState>(Loading)

  val photos: Flow<PagingData<Photo>> = getPaginatedImagesUseCase.perform(ImageParamsRequest("movies", "landscape"))
}

sealed class ImageState {
  object Loading : ImageState()
  data class Success(val urls: List<String>) : ImageState()
  data class Error(val message: String) : ImageState()
  object NetworkError : ImageState()
}