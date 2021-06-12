package com.compose.hotstarclone.data.repository

import com.compose.hotstarclone.data.SafeResult
import com.compose.hotstarclone.data.remote.model.ImageResponse
import com.compose.hotstarclone.data.remote.model.LandscapeResponse
import com.compose.hotstarclone.data.sources.LandscapeRemoteSource

class LandscapeImagesRepo(
  private val landscapeRemoteSource: LandscapeRemoteSource
) {
  suspend fun fetchLandscapeImages(
  ): SafeResult<LandscapeResponse> {
    return landscapeRemoteSource.getLandscapeImages()
  }
}