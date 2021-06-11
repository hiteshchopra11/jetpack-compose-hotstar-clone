package com.compose.hotstarclone.domain.injection

import com.compose.hotstarclone.data.repository.ImagesRepo
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
  fun provideGetAllContents(imagesRepo: ImagesRepo): GetPaginatedImagesUseCase {
    return GetPaginatedImagesUseCase(imagesRepo)
  }
}
