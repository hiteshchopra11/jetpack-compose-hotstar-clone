package com.compose.hotstarclone.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.compose.hotstarclone.data.remote.model.Photo
import com.compose.hotstarclone.ui.theme.drawerBackground
import com.google.accompanist.coil.rememberCoilPainter
import kotlinx.coroutines.flow.Flow

@Composable
fun PaginatedImages(items: Flow<PagingData<Photo>>) {
  val lazyPhotoItems = items.collectAsLazyPagingItems()
  LazyRow(
    modifier = Modifier
      .padding(12.dp, 4.dp)
      .height(180.dp)
  ) {
    items(
      lazyPhotoItems
    ) { item ->
      Card(
        modifier = Modifier
          .height(180.dp)
          .background(drawerBackground)
          .padding(4.dp)
          .width(120.dp),
        shape = RoundedCornerShape(5.dp)
      ) {
        Image(
          painter = rememberCoilPainter(item!!.src.portrait),
          contentDescription = null,
          modifier = Modifier.fillMaxSize(),
          contentScale = ContentScale.Crop
        )
      }
    }
  }
}
