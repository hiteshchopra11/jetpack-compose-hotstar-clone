package com.example.androiddevchallenge.repository

import com.example.androiddevchallenge.data.remote.ImagesApi
import com.example.androiddevchallenge.data.remote.responses.Landscape
import com.example.androiddevchallenge.repository.utils.SafeResult
import java.lang.Exception
import javax.inject.Inject

class ImagesRepoImpl @Inject constructor(private val api: ImagesApi) : ImagesRepo {
  override suspend fun getLandscapeImages():SafeResult<Landscape>{
    return try{
      val result = api.fetchLandscapeImages()
      SafeResult.Success(result)
    } catch (e:Exception){
      SafeResult.Failure(exception = e)
    }
  }
}