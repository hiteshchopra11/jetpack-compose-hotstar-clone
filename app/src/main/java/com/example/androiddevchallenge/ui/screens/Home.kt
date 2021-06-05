package com.example.androiddevchallenge.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.TopBar

@Composable
fun Home(openDrawer: () -> Unit) {
  Column(modifier = Modifier.fillMaxSize()) {
    TopBar(
      title = "Home",
      leadingButtonIcon = Icons.Filled.Menu,
      trailingButtonIcon = Icons.Filled.Search,
      onButtonClicked = { openDrawer() }
    )
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(text = "Home Page content here.")
    }
  }
}

@Preview
@Composable
fun show() {
  Home({})
}