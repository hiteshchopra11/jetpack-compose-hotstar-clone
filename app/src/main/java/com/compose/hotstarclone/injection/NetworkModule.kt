package com.compose.hotstarclone.injection

import com.compose.hotstarclone.BuildConfig
import com.compose.hotstarclone.util.AppConstants.BASE_URL
import com.compose.hotstarclone.data.remote.PaginatedImagesApi
import com.compose.hotstarclone.data.repository.ImagesRepo
import com.compose.hotstarclone.data.sources.ImageRemoteSource
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

  private val okHttpLoggingInterceptor by lazy {
    HttpLoggingInterceptor().apply {
      level =
        if (BuildConfig.DEBUG)
          HttpLoggingInterceptor.Level.BODY
        else
          HttpLoggingInterceptor.Level.NONE
    }
  }

  @Provides
  @Singleton
  fun createHttpClient(): OkHttpClient {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(Level.BODY)
    return OkHttpClient.Builder()
      .addInterceptor(Interceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
          .addHeader("Authorization", BuildConfig.API_KEY)
          .build()
        chain.proceed(newRequest)
      }).addInterceptor(okHttpLoggingInterceptor).build()
  }

  @Provides
  @Singleton
  fun providesApi(client: OkHttpClient): PaginatedImagesApi {
    return Retrofit.Builder()
      .client(client)
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl(BASE_URL)
      .build()
      .create(PaginatedImagesApi::class.java)
  }
}