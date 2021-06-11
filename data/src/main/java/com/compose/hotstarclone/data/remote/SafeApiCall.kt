package com.compose.hotstarclone.data.remote

import com.compose.hotstarclone.data.SafeResult
import com.compose.hotstarclone.data.SafeResult.Failure
import com.compose.hotstarclone.data.SafeResult.NetworkError
import com.compose.hotstarclone.data.SafeResult.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

suspend fun <T> safeApiCall(
  dispatcher: CoroutineDispatcher,
  apiCall: suspend () -> T,
): SafeResult<T> {
  return withContext(dispatcher) {
    try {
      Success(apiCall.invoke())
    } catch (throwable: Throwable) {
      Timber.e(throwable.message.toString())
      when (throwable) {
        is IOException -> NetworkError
        is HttpException -> Failure(throwable)
        else -> Failure(Exception(throwable))
      }
    }
  }
}