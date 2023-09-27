package com.example.mybilibili.componentTest

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage

@Composable
@Preview
fun CoilTest(){
    AsyncImage(
        modifier = Modifier.fillMaxSize().background(Color.Gray),
        model = "https://i2.hdslb.com/bfs/archive/c30e65d402c2bc0ad76f7fed246eca563c514b34.jpg@672w_378h_1c_!web-home-common-cover.webp",
        contentDescription = "")
}