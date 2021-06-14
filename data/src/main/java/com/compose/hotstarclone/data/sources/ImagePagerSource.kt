package com.compose.hotstarclone.data.sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.compose.hotstarclone.data.SafeResult.NetworkError
import com.compose.hotstarclone.data.SafeResult.Success
import com.compose.hotstarclone.data.getErrorOrNull
import com.compose.hotstarclone.data.getSuccessOrNull
import com.compose.hotstarclone.data.model.ImageParamsRequest
import com.compose.hotstarclone.data.remote.model.Photo
import java.io.IOException

class ImagePagerSource(
  private val imageRemoteSource: ImageRemoteSource,
  private val imagesParamsRequest: ImageParamsRequest,
) : PagingSource<Int, Photo>() {
  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
    return try {
      // Start refresh at page 1 if undefined.
      val page = params.key ?: 1
      when (val result =
        imageRemoteSource.getAllImagesByQuery(
          imagesParamsRequest.query,
          imagesParamsRequest.orientation,
          ImageRemoteSource.PAGE_SIZE_DEFAULT,
          page
        )
      ) {
        is Success -> {
          val paginatedContent = result.getSuccessOrNull() ?: return LoadResult.Error(Exception())
          LoadResult.Page(
            data = paginatedContent.photos,
            prevKey = if (page == 1) null else page - 1,
            nextKey = page + 1
          )
        }
        is NetworkError -> {
          LoadResult.Error(IOException())
        }
        else -> {
          LoadResult.Error(result.getErrorOrNull()?.exception ?: Exception())
        }
      }
    } catch (exception: Exception) {
      LoadResult.Error(exception)
    }
  }

  override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
    return state.anchorPosition?.let { anchorPosition ->
      val anchorPage = state.closestPageToPosition(anchorPosition)
      anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }
  }
}
