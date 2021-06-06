package com.example.androiddevchallenge.repository

import com.example.androiddevchallenge.data.remote.ImagesApi
import com.example.androiddevchallenge.data.remote.responses.Landscape
import com.example.androiddevchallenge.repository.utils.SafeResult
import java.lang.Exception
import javax.inject.Inject

class ImagesRepoImpl @Inject constructor(private val api: ImagesApi) : ImagesRepo {
  override suspend fun getLandscapeImages(): SafeResult<Landscape> {
    try {
      val result = api.fetchLandscapeImages()
      return SafeResult.Success(result)
    } catch (e: Exception) {
      return SafeResult.Failure(exception = e)
    }
  }
}