package com.compose.hotstarclone.data.sources

import com.compose.hotstarclone.data.SafeResult
import com.compose.hotstarclone.data.remote.model.ImageResponse

interface ImageRemoteSource {
  companion object {
    const val PAGE_SIZE_DEFAULT = 5
  }

  suspend fun getAllImagesByQuery(
    query: String,
    orientation: String,
    pageSize: Int,
    page: Int?,
  ): SafeResult<ImageResponse>
}