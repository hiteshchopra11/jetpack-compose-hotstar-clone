package com.compose.hotstarclone.domain.injection

import com.compose.hotstarclone.data.repository.LandscapeImagesRepo
import com.compose.hotstarclone.data.repository.PaginatedImagesRepo
import com.compose.hotstarclone.domain.usecase.GetLandscapeImagesUseCase
import com.compose.hotstarclone.domain.usecase.GetPaginatedImagesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

  @Provides
  @Singleton
  fun provideGetAllContents(paginatedImagesRepo: PaginatedImagesRepo): GetPaginatedImagesUseCase {
    return GetPaginatedImagesUseCase(paginatedImagesRepo)
  }

  @Provides
  @Singleton
  fun provideGetLandscapeImages(landscapeImagesRepo: LandscapeImagesRepo): GetLandscapeImagesUseCase {
    return GetLandscapeImagesUseCase(landscapeImagesRepo)
  }
}
