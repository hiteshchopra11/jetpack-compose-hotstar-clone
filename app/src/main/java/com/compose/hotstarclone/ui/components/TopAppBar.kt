package com.compose.hotstarclone.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.compose.hotstarclone.R.drawable
import com.compose.hotstarclone.ui.theme.drawerBackground
import kotlinx.coroutines.Job

@Composable
fun TopAppToolbar(openDrawer: () -> Job) {
  TopAppBar(
    title = {
      Icon(
        painter = painterResource(drawable.ic_logo),
        contentDescription = "Hotstar Clone",
        tint = Color.White,
        modifier = Modifier
          .size(120.dp)
          .background(Color.Transparent)
      )
    },
    navigationIcon = {
      IconButton(onClick = { openDrawer() }) {
        Icon(imageVector = Filled.Menu, contentDescription = "Icon", tint = Color.White)
      }
    },
    actions = {
      IconButton(onClick = {}) {
        Icon(imageVector = Filled.Search, contentDescription = "Icon", tint = Color.White)
      }
    },
    backgroundColor = drawerBackground,
  )
}