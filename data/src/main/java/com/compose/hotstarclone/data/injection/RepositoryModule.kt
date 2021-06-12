package com.compose.hotstarclone.data.injection

import com.compose.hotstarclone.data.repository.LandscapeImagesRepo
import com.compose.hotstarclone.data.repository.PaginatedImagesRepo
import com.compose.hotstarclone.data.sources.ImageRemoteSourceImpl
import com.compose.hotstarclone.data.sources.LandscapeRemoteSource
import com.compose.hotstarclone.data.sources.LandscapeRemoteSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

  @Provides
  @Singleton
  fun providePaginatedImagesRepo(imageRemoteSourceImpl: ImageRemoteSourceImpl): PaginatedImagesRepo {
    return PaginatedImagesRepo(imageRemoteSourceImpl)
  }

  @Provides
  @Singleton
  fun provideLandscapeImagesRepo(landscapeRemoteSource: LandscapeRemoteSourceImpl): LandscapeImagesRepo {
    return LandscapeImagesRepo(landscapeRemoteSource)
  }
}