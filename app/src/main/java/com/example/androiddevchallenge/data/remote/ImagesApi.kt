package com.example.androiddevchallenge.data.remote

import com.example.androiddevchallenge.data.remote.responses.Landscape
import retrofit2.http.GET

interface ImagesApi {

  @GET
  suspend fun fetchLandscapeImages(): Landscape
}