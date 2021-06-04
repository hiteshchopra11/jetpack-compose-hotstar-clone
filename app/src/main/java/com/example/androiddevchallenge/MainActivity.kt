package com.example.androiddevchallenge

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.Button
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Text
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.Drawer
import com.example.androiddevchallenge.ui.theme.MyTheme
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context: Context = applicationContext
        setContent {
            MyTheme {
                MyScreenContent(context)
            }
        }
    }
}

@Composable
fun MyScreenContent(context: Context) {
    Column(modifier = Modifier.fillMaxHeight()) {
        Drawer()
    }
}


@Preview
@Composable
fun Show() {
    MyTheme {
        Column(modifier = Modifier.fillMaxHeight()) {
            Drawer()
        }
    }
}