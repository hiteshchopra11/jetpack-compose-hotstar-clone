package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.routes.screens
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.drawerBackground
import com.example.androiddevchallenge.ui.theme.drawerItemSubtitle
import com.example.androiddevchallenge.ui.theme.drawerItemTitle

@Composable
fun Drawer(
  modifier: Modifier = Modifier,
  onDestinationClicked: (route: String) -> Unit,
) {
  Row {
    Column(
      modifier
        .background(drawerBackground)
        .fillMaxSize()
        .padding(top = 30.dp)
    ) {
      Text(
        "Hitesh Chopra",
        color = drawerItemTitle,
        fontSize = 24.sp,
        modifier = Modifier.padding(start = 24.dp)
      )
      Spacer(modifier = Modifier.padding(4.dp))
      Text(
        "+91 7009959730",
        color = drawerItemSubtitle,
        fontSize = 18.sp,
        modifier = Modifier.padding(start = 24.dp)
      )
      Spacer(modifier = Modifier.padding(16.dp))
      Box(
        modifier = Modifier
          .height(50.dp)
          .fillMaxWidth()
          .background(Color(0xFF1b1f20))
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            painterResource(id = R.drawable.ic_kids),
            contentDescription = "",
            tint = Color(0xFFF1bf5e),
            modifier = Modifier
              .fillMaxHeight()
              .padding(16.dp, 16.dp, 6.dp, 16.dp)
          )
          Text(
            "Safe",
            color = Color(0xFFF1bf5e),
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.istok_bold))
          )
        }
      }
      screens.forEach { screen ->
        Spacer(modifier = Modifier.height(24.dp))
        Row(modifier = Modifier
          .padding(start = 16.dp)
          .clickable {
            onDestinationClicked("home")
          }) {
          if (screen.subtitle == null) {
            Icon(
              painterResource(id = screen.icon),
              contentDescription = null,
              tint = Color(0xFF8F9999),
            )
          } else {
            Icon(
              painterResource(id = screen.icon),
              contentDescription = null,
              tint = Color(0xFF8F9999),
              modifier = Modifier
                .padding(0.dp, 12.dp, 0.dp, 0.dp)
            )
          }
          Spacer(modifier = Modifier.padding(8.dp))
          Column {
            Text(
              text = screen.title,
              color = drawerItemTitle,
              fontSize = 20.sp
            )
            screen.subtitle?.let {
              Text(
                text = it,
                color = drawerItemSubtitle,
                fontSize = 16.sp
              )
            }
          }
        }
      }
    }
    Spacer(modifier = Modifier.padding(8.dp))
  }
}

@Preview
@Composable
fun DrawerPreview() {
  MyTheme {
    Drawer(onDestinationClicked = {})
  }
}