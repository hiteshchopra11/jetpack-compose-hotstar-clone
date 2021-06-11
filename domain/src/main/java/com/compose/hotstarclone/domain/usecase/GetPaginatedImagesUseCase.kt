package com.compose.hotstarclone.domain.usecase

import androidx.paging.PagingData
import com.compose.hotstarclone.data.model.ImageParamsRequest
import com.compose.hotstarclone.data.remote.model.Photo
import com.compose.hotstarclone.data.repository.ImagesRepo
import kotlinx.coroutines.flow.Flow

class GetPaginatedImagesUseCase(private val imagesRepo: ImagesRepo) :
  BaseUseCase<Flow<PagingData<Photo>>, ImageParamsRequest> {
  override fun perform(params: ImageParamsRequest): Flow<PagingData<Photo>> {
    return imagesRepo.getImages(params)
  }
}