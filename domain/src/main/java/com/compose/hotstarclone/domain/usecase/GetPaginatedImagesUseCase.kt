package com.compose.hotstarclone.domain.usecase

import androidx.paging.PagingData
import com.compose.hotstarclone.data.model.ImageParamsRequest
import com.compose.hotstarclone.data.remote.model.Photo
import com.compose.hotstarclone.data.repository.PaginatedImagesRepo
import kotlinx.coroutines.flow.Flow

class GetPaginatedImagesUseCase(private val paginatedImagesRepo: PaginatedImagesRepo) :
  BaseUseCase<Flow<PagingData<Photo>>, ImageParamsRequest> {
  override fun perform(params: ImageParamsRequest): Flow<PagingData<Photo>> {
    return paginatedImagesRepo.getImages(params)
  }
}