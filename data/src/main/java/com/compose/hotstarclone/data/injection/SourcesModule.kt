package com.compose.hotstarclone.data.injection

import com.compose.hotstarclone.data.remote.LandscapeImagesApi
import com.compose.hotstarclone.data.remote.PaginatedImagesApi
import com.compose.hotstarclone.data.sources.ImageRemoteSourceImpl
import com.compose.hotstarclone.data.sources.LandscapeRemoteSourceImpl
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

  @Provides
  @Singleton
  fun provideLandscapeImageRemoteSource(landscapeImagesApi: LandscapeImagesApi): LandscapeRemoteSourceImpl {
    return LandscapeRemoteSourceImpl(landscapeImagesApi)
  }
}