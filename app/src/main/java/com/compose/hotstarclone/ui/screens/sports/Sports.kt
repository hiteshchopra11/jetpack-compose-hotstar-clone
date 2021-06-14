package com.compose.hotstarclone.ui.screens.sports

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign.Center
import com.compose.hotstarclone.ui.theme.drawerBackground

@Composable
fun Sports() {
  Column(
    Modifier
      .fillMaxSize()
      .background(drawerBackground),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = "Sports",
      color = Color.White,
      textAlign = Center
    )
  }
}
