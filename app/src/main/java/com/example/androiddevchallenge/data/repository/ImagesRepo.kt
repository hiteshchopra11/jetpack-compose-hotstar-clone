package com.example.androiddevchallenge.data.repository

import com.example.androiddevchallenge.data.remote.responses.ImageResponse
import com.example.androiddevchallenge.data.remote.responses.Landscape
import com.example.androiddevchallenge.data.repository.utils.SafeResult

interface ImagesRepo {
  suspend fun fetchImages(
    query: String,
    orientation: String,
    page: Int,
    per_page: Int
  ): SafeResult<ImageResponse>
}