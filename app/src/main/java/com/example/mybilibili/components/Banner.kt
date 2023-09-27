package com.example.mybilibili.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview(showBackground = true)
fun Banner() {
    var selected by remember {
        mutableStateOf(0)
    }

    val scope = rememberCoroutineScope()


    val bannerList = listOf(0,1, 2, 3, 4)
    val pagerState = rememberPagerState()

    LaunchedEffect(pagerState.currentPage){
        while (true){
            var toPage = pagerState.currentPage + 1
            delay(2000)
            pagerState.animateScrollToPage(toPage)
        }
    }
    Box(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(3))
            .aspectRatio(16f / 9f)
    ) {
        HorizontalPager(
            pageCount = bannerList.size,
            pageSpacing = 10.dp,
            state = pagerState
        ) {

            AsyncImage(
                model = "", contentDescription = "", modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(3))
                    .background(
                        Color.Gray
                    ),

            )
            Column {
                Text(text = bannerList[it].toString())
                Text(text = "${pagerState.currentPage}")
                Text(text = "${pagerState.currentPageOffsetFraction}")
            }

        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
        ) {
            bannerList.forEachIndexed { index, banner ->
                if (index == pagerState.currentPage)
                    Box(modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .background(Color.Blue, CircleShape)
                        .size(12.dp))
                else
                    Box(modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .background(Color.Red, CircleShape)
                        .size(12.dp))
            }
        }

    }
}