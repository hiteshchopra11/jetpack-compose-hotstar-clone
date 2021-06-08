package com.example.androiddevchallenge.data.remote

import com.example.androiddevchallenge.data.remote.responses.ImageResponse
import com.example.androiddevchallenge.data.remote.responses.Landscape
import com.example.androiddevchallenge.data.repository.utils.SafeResult
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ImagesApi {
  @Headers("Authorization:563492ad6f9170000100000157d4194866fc4b4bac6ceff8d413a181")
  @GET("/v1/search")

  suspend fun fetchImages(
    @Query("query") query: String,
//    @Query("orientation") orientation: String,
//    @Query("page") page: Int,
//    @Query("per_page") per_page: Int,
  ): ImageResponse
}