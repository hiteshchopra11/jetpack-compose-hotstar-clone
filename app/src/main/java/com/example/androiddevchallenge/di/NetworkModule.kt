package com.example.androiddevchallenge.di

import com.example.androiddevchallenge.BuildConfig
import com.example.androiddevchallenge.util.AppConstants.BASE_URL
import com.example.androiddevchallenge.data.remote.ImagesApi
import com.example.androiddevchallenge.data.repository.ImagesRepo
import com.example.androiddevchallenge.data.repository.ImagesRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun createHttpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(Level.BODY)
    return OkHttpClient.Builder().addInterceptor(logging).build()
//      .addInterceptor(Interceptor { chain ->
//      val newRequest: Request = chain.request().newBuilder()
//        .addHeader("Authorization", BuildConfig.API_KEY)
//        .build()
//      chain.proceed(newRequest)
//    }).build()
  }

  @Provides
  @Singleton
  fun providesApi(client: OkHttpClient): ImagesApi {
    return Retrofit.Builder()
      .client(client)
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