package com.compose.hotstarclone.data.remote

import com.compose.hotstarclone.data.remote.model.ImageResponse
import com.compose.hotstarclone.data.remote.model.LandscapeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LandscapeImagesApi {
  @GET("api/v1/images")
  suspend fun fetchLandscapeImages(): LandscapeResponse
}