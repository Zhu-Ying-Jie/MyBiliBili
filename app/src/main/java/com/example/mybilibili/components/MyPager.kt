package com.example.mybilibili.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.sp
import com.example.mybilibili.pages.RecommendPage
import com.example.mybilibili.pojo.Tab

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyPager(
    selected: MutableState<Int>,
    pagerState: PagerState,
    tabs: List<Tab>,
    listNestedScrollConnection: NestedScrollConnection,
    gridNestedScrollConnection: NestedScrollConnection
) {
//    val tab = listOf(1,2,3)
//    val pagerState = rememberPagerState()

    HorizontalPager(
        pageCount = tabs.size,
        state = pagerState,
        modifier = Modifier.nestedScroll(gridNestedScrollConnection)
    ) { index ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {

            when (tabs[index].label) {
                "推荐" -> RecommendPage()
                "直播" -> MyScrollableTabRow(selected, tabs, pagerState)
                else ->
                    Text(
                        fontSize = 40.sp,
                        text = tabs[index].label,
                    )
            }
        }
    }
}