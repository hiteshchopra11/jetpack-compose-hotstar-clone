package com.example.androiddevchallenge.data.remote

import com.example.androiddevchallenge.data.remote.responses.Landscape
import com.example.androiddevchallenge.repository.utils.SafeResult
import retrofit2.http.GET

interface ImagesApi {

  @GET("/images")
  suspend fun fetchLandscapeImages(): Landscape
}