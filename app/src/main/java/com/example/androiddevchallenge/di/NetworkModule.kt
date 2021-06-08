package com.example.androiddevchallenge.di

import com.example.androiddevchallenge.util.AppConstants.BASE_URL
import com.example.androiddevchallenge.data.remote.ImagesApi
import com.example.androiddevchallenge.repository.ImagesRepo
import com.example.androiddevchallenge.repository.ImagesRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
  @Provides
  @Singleton
  fun providesApi(): ImagesApi {
    return Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl(BASE_URL)
      .build()
      .create(ImagesApi::class.java)
  }

  @Provides
  @Singleton
  fun providesRepository(api: ImagesApi): ImagesRepo {
    return ImagesRepoImpl(api)
  }
}