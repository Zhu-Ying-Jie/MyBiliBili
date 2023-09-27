package com.example.mybilibili.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mybilibili.pojo.Tab
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyTabRow(selected: MutableState<Int>, tabs: List<Tab>, pagerState: PagerState) {

    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                    .wrapContentSize(Alignment.BottomCenter)
                    .padding(horizontal = 8.dp),
                color = MaterialTheme.colorScheme.primaryContainer
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->

            val beSelected = (index == pagerState.currentPage)

            Tab(selected = (beSelected),
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .height(30.dp),
                onClick = {
                    selected.value = index
                    scope.launch {
                        if ((selected.value - pagerState.currentPage).absoluteValue == 1)
                            pagerState.animateScrollToPage(index)
                        else {
                            pagerState.scrollToPage(index)
                        }
                    }
                }) {

                val targetSize = animateFloatAsState(
                    targetValue = if (beSelected) 15f else 13f,
                    label = ""
                )
                val targetColor = animateColorAsState(
                    targetValue = if (beSelected) Color.Black else Color.Gray,
                    label = "",)
                Text(text = tab.label, fontSize = targetSize.value.sp, color = targetColor.value)

            }
        }
    }
}