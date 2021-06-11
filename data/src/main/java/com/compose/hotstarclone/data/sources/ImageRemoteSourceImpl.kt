package com.compose.hotstarclone.data.sources

import com.compose.hotstarclone.data.SafeResult
import com.compose.hotstarclone.data.remote.PaginatedImagesApi

import com.compose.hotstarclone.data.remote.model.ImageResponse
import com.compose.hotstarclone.data.remote.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class ImageRemoteSourceImpl(
  private val paginatedImagesApi: PaginatedImagesApi,
  private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ImageRemoteSource {
  override suspend fun getAllImagesByQuery(
    query: String,
    orientation: String,
    pageSize: Int,
    page: Int?
  ): SafeResult<ImageResponse> {
    return safeApiCall(dispatcher) {
      paginatedImagesApi.fetchImages(query, orientation, pageSize, page)
    }
  }
}