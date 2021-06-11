package com.compose.hotstarclone.data.injection

import com.compose.hotstarclone.data.repository.ImagesRepo
import com.compose.hotstarclone.data.sources.ImageRemoteSourceImpl
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
  fun provideContentRepository(imageRemoteSourceImpl: ImageRemoteSourceImpl): ImagesRepo {
    return ImagesRepo(imageRemoteSourceImpl)
  }
}