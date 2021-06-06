package com.example.androiddevchallenge.repository

import com.example.androiddevchallenge.data.remote.responses.Landscape
import com.example.androiddevchallenge.repository.utils.SafeResult

interface ImagesRepo {
  suspend fun getLandscapeImages(): SafeResult<Landscape>
}