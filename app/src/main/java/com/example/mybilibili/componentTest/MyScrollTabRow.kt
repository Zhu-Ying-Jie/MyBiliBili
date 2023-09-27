package com.example.mybilibili.componentTest

import androidx.compose.material.Text
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview(showBackground = true)
fun test(){

    val selected = remember {
        mutableStateOf(0)
    }
    ScrollableTabRow(selectedTabIndex = selected.value) {
        repeat(10){ index ->
            Tab(selected = index == selected.value, onClick = { selected.value = index }) {
                Text(text = index.toString())
            }
        }
    }
}

