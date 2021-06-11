package com.compose.hotstarclone.data.injection

import com.compose.hotstarclone.data.remote.PaginatedImagesApi
import com.compose.hotstarclone.data.repository.ImagesRepo
import com.compose.hotstarclone.data.sources.ImageRemoteSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SourcesModule {

  @Provides
  @Singleton
  fun provideSearchRemoteSource(imagesApi: PaginatedImagesApi): ImageRemoteSourceImpl {
    return ImageRemoteSourceImpl(imagesApi)
  }
}