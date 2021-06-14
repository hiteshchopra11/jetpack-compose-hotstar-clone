package com.compose.hotstarclone.data.remote

import com.compose.hotstarclone.data.remote.model.ImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PaginatedImagesApi {
  @GET("/v1/search")
  suspend fun fetchImages(
    @Query("query") query: String,
    @Query("orientation") orientation: String,
    @Query("page") page: Int,
    @Query("per_page") per_page: Int?,
  ): ImageResponse
}