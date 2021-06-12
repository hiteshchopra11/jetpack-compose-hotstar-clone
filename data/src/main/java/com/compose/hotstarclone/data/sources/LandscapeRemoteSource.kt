package com.compose.hotstarclone.data.sources

import com.compose.hotstarclone.data.SafeResult
import com.compose.hotstarclone.data.remote.model.LandscapeResponse

interface LandscapeRemoteSource {
  suspend fun getLandscapeImages(
  ): SafeResult<LandscapeResponse>
}