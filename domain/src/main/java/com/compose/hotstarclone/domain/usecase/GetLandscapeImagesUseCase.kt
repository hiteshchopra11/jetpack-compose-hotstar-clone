package com.compose.hotstarclone.domain.usecase

import androidx.paging.PagingData
import com.compose.hotstarclone.data.SafeResult
import com.compose.hotstarclone.data.model.ImageParamsRequest
import com.compose.hotstarclone.data.remote.model.LandscapeResponse
import com.compose.hotstarclone.data.remote.model.Photo
import com.compose.hotstarclone.data.repository.LandscapeImagesRepo
import com.compose.hotstarclone.data.repository.PaginatedImagesRepo
import kotlinx.coroutines.flow.Flow

class GetLandscapeImagesUseCase(private val landscapeImagesRepo: LandscapeImagesRepo) :
  BaseUseCase<SafeResult<LandscapeResponse>, Unit> {
  override suspend fun performAsync(): SafeResult<LandscapeResponse> {
    return landscapeImagesRepo.fetchLandscapeImages()
  }
}