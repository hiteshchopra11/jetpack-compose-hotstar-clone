package com.example.androiddevchallenge.data.repository

import com.example.androiddevchallenge.data.remote.ImagesApi
import com.example.androiddevchallenge.data.remote.responses.ImageResponse
import com.example.androiddevchallenge.data.remote.responses.Landscape
import com.example.androiddevchallenge.data.repository.utils.SafeResult
import java.lang.Exception
import javax.inject.Inject

class ImagesRepoImpl(private val api: ImagesApi) : ImagesRepo {

  override suspend fun fetchImages(
    query: String,
    orientation: String,
    page: Int,
    per_page: Int
  ): SafeResult<ImageResponse> {
    return try {
      val result = api.fetchImages("movies")
      SafeResult.Success(result)
    } catch (e: Exception) {
      SafeResult.Failure(exception = e)
    }
  }
}