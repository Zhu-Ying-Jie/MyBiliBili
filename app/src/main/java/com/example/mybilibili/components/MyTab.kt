package com.example.mybilibili.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview(showBackground = true)
fun MyTab(){
    Box (
        Modifier
            .width(60.dp)
            .height(45.dp)
            .padding(5.dp),
        contentAlignment = Alignment.Center){

        Text(text = "直播")
    }
}