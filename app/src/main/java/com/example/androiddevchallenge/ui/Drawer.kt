package com.example.androiddevchallenge.ui

import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.MyTheme

sealed class DrawerScreens(val title: String) {
    object Home : DrawerScreens("Home")
    object Account : DrawerScreens("Account")
    object Help : DrawerScreens( "Help")
}

private val screens = listOf(
    DrawerScreens.Home,
    DrawerScreens.Account,
    DrawerScreens.Help
)

@Composable
fun Drawer(
    modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 48.dp)
    ) {
        Text("Hitesh Chopra")
        Text("7009959730")
        screens.forEach { screen ->
            Spacer(Modifier.height(24.dp))
            Text(
                text = screen.title,
                style = MaterialTheme.typography.h4
            )
        }
    }
}

@Preview
@Composable
fun DrawerPreview() {
    MyTheme {
        Drawer()
    }
}