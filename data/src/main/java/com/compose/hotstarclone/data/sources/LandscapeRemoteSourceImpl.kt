package com.compose.hotstarclone.data.sources

import com.compose.hotstarclone.data.SafeResult
import com.compose.hotstarclone.data.remote.LandscapeImagesApi
import com.compose.hotstarclone.data.remote.model.LandscapeResponse
import com.compose.hotstarclone.data.remote.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class LandscapeRemoteSourceImpl(
  private val landscapeImagesApi: LandscapeImagesApi,
  private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : LandscapeRemoteSource {
  override suspend fun getLandscapeImages(): SafeResult<LandscapeResponse> {
    return safeApiCall(dispatcher) {
      landscapeImagesApi.fetchLandscapeImages()
    }
  }
}