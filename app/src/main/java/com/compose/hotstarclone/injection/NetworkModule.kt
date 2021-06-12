package com.compose.hotstarclone.injection

import com.compose.hotstarclone.BuildConfig
import com.compose.hotstarclone.data.remote.LandscapeImagesApi
import com.compose.hotstarclone.util.AppConstants.PAGINATED_BASE_URL
import com.compose.hotstarclone.data.remote.PaginatedImagesApi
import com.compose.hotstarclone.injection.qualifiers.LandscapeOkHttpClient
import com.compose.hotstarclone.injection.qualifiers.PaginatedOkHttpClient
import com.compose.hotstarclone.util.AppConstants.LANDSCAPE_BASE_URL
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
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  private val okHttpLoggingInterceptor by lazy {
    HttpLoggingInterceptor().apply {
      level = if (BuildConfig.DEBUG) BODY else NONE
    }
  }

  @Provides
  @Singleton
  @LandscapeOkHttpClient
  fun createLandscapeImagesOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().addInterceptor(okHttpLoggingInterceptor).build()
  }

  @Provides
  @Singleton
  @PaginatedOkHttpClient
  fun createPaginatedImagesOkHttpClient(): OkHttpClient {
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
  fun providesPaginatedImagesApi(@PaginatedOkHttpClient client: OkHttpClient): PaginatedImagesApi {
    return Retrofit.Builder()
      .client(client)
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl(PAGINATED_BASE_URL)
      .build()
      .create(PaginatedImagesApi::class.java)
  }

  @Provides
  @Singleton
  fun provideLandscapeImagesApi(@LandscapeOkHttpClient client: OkHttpClient): LandscapeImagesApi {
    return Retrofit.Builder()
      .client(client)
      .addConverterFactory(GsonConverterFactory.create())
      .baseUrl(LANDSCAPE_BASE_URL)
      .build()
      .create(LandscapeImagesApi::class.java)
  }
}