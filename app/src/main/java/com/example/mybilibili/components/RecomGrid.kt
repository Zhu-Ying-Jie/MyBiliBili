package com.example.mybilibili.components

import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mybilibili.network.Item

@Composable
fun RecommendGrid(videos: State<List<Item>>) {


    LazyVerticalGrid(

        modifier = Modifier.padding(horizontal = 8.dp),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        userScrollEnabled = true,
        flingBehavior = ScrollableDefaults.flingBehavior()
    ) {
//        item(span = { GridItemSpan(2) }) { Box(modifier = Modifier.height(1.dp)) }
        videos.value.forEachIndexed { index, _ ->
            item(content = {
                VideoCard(videos.value[index])
            })
        }

    }

}