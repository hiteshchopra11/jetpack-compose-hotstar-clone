package com.example.androiddevchallenge.ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.drawerBackground
import com.example.androiddevchallenge.ui.theme.drawerItemTitle

@Composable
fun TopBar(
  title: String = "",
  leadingButtonIcon: ImageVector,
  trailingButtonIcon: ImageVector,
  onButtonClicked: () -> Unit
) {
  TopAppBar(
    title = {
      Text(
        text = title,
        color = drawerItemTitle,
        style = TextStyle(fontStyle = FontStyle.Normal, fontSize = 16.sp),
      )
    },
    navigationIcon = {
      IconButton(onClick = { onButtonClicked() }) {
        Icon(leadingButtonIcon, contentDescription = "", tint = drawerItemTitle)
      }
    },
    actions = {
      IconButton(onClick = { }) {
        Icon(trailingButtonIcon, contentDescription = "", tint = drawerItemTitle)
      }
    },
    backgroundColor = drawerBackground
  )
}

@Preview
@Composable
fun Preview() {
  TopBar(
    title = "Disney Hotstar",
    leadingButtonIcon = Filled.Menu,
    trailingButtonIcon = Filled.Search
  ) {}
}