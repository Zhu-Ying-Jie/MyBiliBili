package com.example.mybilibili

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import com.example.mybilibili.components.MyNavigationBar
import com.example.mybilibili.screens.HomeScreen
import com.example.mybilibili.ui.theme.MyBiliBiliTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            try {
                Class
                    .forName("androidx.compose.material3.TabRowKt")
                    .getDeclaredField("ScrollableTabRowMinimumTabWidth").apply {
                        isAccessible = true
                    }.set(this, 0f)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            MyBiliBiliTheme {
                val focusManager = LocalFocusManager.current

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(
                            onClick = { focusManager.clearFocus() },
                            indication = null,
                            interactionSource = remember {
                                MutableInteractionSource()
                            }
                        ),
                    color = MaterialTheme.colorScheme.background)
                {
                    MainPage()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainPage() {
    Scaffold(
        bottomBar = { MyNavigationBar() },
        content = { paddingValues ->
            Column(
                Modifier
                    .padding(paddingValues)) {
                HomeScreen()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyBiliBiliTheme {
        MainPage()
    }
}