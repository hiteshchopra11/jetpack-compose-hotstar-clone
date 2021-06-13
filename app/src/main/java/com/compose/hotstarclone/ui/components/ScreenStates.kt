package com.compose.hotstarclone.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.hotstarclone.R

@Composable
fun LoadingView(
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    CircularProgressIndicator(color = Color.White)
  }
}

@Composable
fun LoadingItem() {
  CircularProgressIndicator(
    color = Color.White,
    modifier = Modifier
      .fillMaxHeight()
      .padding(16.dp)
      .wrapContentSize(Alignment.Center)
  )
}

@Composable
fun DisplayPaginatedError(
  message: String,
  modifier: Modifier = Modifier,
  onClickRetry: () -> Unit
) {
  Column(
    modifier = modifier.padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = message,
      color = Color.White
    )
    Spacer(modifier = Modifier.padding(0.dp, 4.dp))
    IconButton(onClick = onClickRetry) {
      Icon(
        painter = painterResource(id = R.drawable.ic_retry),
        contentDescription = "",
        tint = Color.White
      )
    }
  }
}

@Composable
fun DisplayError(error: Error? = null, message: String? = null) {
  Column(
    Modifier
      .height(200.dp)
      .fillMaxWidth(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    error?.message ?: message?.let { Text(text = it, color = Color.White) }
  }
}