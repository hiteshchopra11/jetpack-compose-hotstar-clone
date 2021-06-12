package com.compose.hotstarclone.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.compose.hotstarclone.data.model.ImageParamsRequest
import com.compose.hotstarclone.data.remote.model.Photo
import com.compose.hotstarclone.data.sources.ImagePagerSource
import com.compose.hotstarclone.data.sources.ImageRemoteSource
import kotlinx.coroutines.flow.Flow

class PaginatedImagesRepo(private val imageRemoteSource: ImageRemoteSource) {

  fun getImages(imageParamsRequest: ImageParamsRequest): Flow<PagingData<Photo>> {
    return Pager(
      PagingConfig(pageSize = 5)
    ) {
      ImagePagerSource(imageRemoteSource, imageParamsRequest)
    }.flow
  }
}