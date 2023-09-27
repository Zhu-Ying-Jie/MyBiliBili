package com.example.mybilibili.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import com.example.mybilibili.components.CollapseAppBar
import com.example.mybilibili.components.MyPager
import com.example.mybilibili.components.MyScrollableTabRow
import com.example.mybilibili.pojo.Tab
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
@Preview
fun HomeScreen() {
    val scope = rememberCoroutineScope()
    val topAppBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(topAppBarState)


    val selected = remember {
        mutableStateOf(1)
    }

    val tabs = listOf(
        Tab("直播"),
        Tab("推荐"),
        Tab("热门"),
        Tab("追番"),
        Tab("影视"),
        Tab("新闻"), Tab("直播"),
        Tab("推荐"),
        Tab("热门"),
        Tab("追番"),
        Tab("影视"),
        Tab("新闻"),
    )

    val pagerState = rememberPagerState(
        initialPage = 1
    )
    val appBarHeight = 60.dp

    val appBarHeightPx = with(LocalDensity.current) { appBarHeight.roundToPx().toFloat() }

    val heightOffset = remember {
        Animatable(0f)
    }

    val listNestedScrollConnection = remember {
        object : NestedScrollConnection {
            var dragCount = 0f
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                dragCount += available.y
                var consume = available.y;
                if (dragCount <= -appBarHeightPx) {
                    dragCount = (-appBarHeightPx)
                    consume = 0f
                }
                if (dragCount >= 0) {
                    dragCount = 0f
                    consume = 0f
                }
                scope.launch {
                    heightOffset.snapTo(dragCount)
                }
                return Offset(x = 0f, y = consume)
            }

            var flingCount = 0f
            override suspend fun onPreFling(available: Velocity): Velocity {

                if (available.y == 0f) {
                    if (heightOffset.value < -appBarHeightPx / 2) {
                        heightOffset.animateTo(-appBarHeightPx)
                    } else {
                        heightOffset.animateTo(0f)
                    }
                    return Velocity.Zero
                }

                flingCount += available.y
                var consume = available.y

                if (flingCount + heightOffset.value <= -appBarHeightPx) {
                    flingCount = 0f
                    consume = 0f
                }
                if (flingCount + heightOffset.value >= 0) {
                    flingCount = 0f
                    consume = 0f
                }

                scope.launch {
                    heightOffset.snapTo(flingCount + heightOffset.value)
                }

                return Velocity(x = 0f, y = consume)
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                if (heightOffset.value < -appBarHeightPx / 2) {
                    heightOffset.animateTo(-appBarHeightPx)
                } else {
                    heightOffset.animateTo(0f)
                }
                return Velocity.Zero
            }
        }
    }

    val gridNestedScrollConnection = remember {
        object : NestedScrollConnection {
            var dragCount = 0f
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {

                dragCount += available.y
                var consume = available.y;
                if (dragCount <= -appBarHeightPx) {
                    dragCount = (-appBarHeightPx)
                    consume = 0f
                }
                if (dragCount >= 0) {
                    dragCount = 0f
                    consume = 0f
                }
                scope.launch {
                    heightOffset.snapTo(dragCount)
                }
                return Offset(x = 0f, y = consume)
            }

            var flingCount = 0f
            override suspend fun onPreFling(available: Velocity): Velocity {

//                if (available.y == 0f) {
//                    if (heightOffset.value < -appBarHeightPx / 2) {
//                        heightOffset.animateTo(-appBarHeightPx)
//                    } else {
//                        heightOffset.animateTo(0f)
//                    }
//                    return Velocity.Zero
//                }

                flingCount += available.y
                var consume = available.y

                if (flingCount + heightOffset.value <= -appBarHeightPx) {
                    flingCount = 0f
                    consume = 0f
                }
                if (flingCount + heightOffset.value >= 0) {
                    flingCount = 0f
                    consume = 0f
                }

                scope.launch {
                    heightOffset.snapTo(flingCount + heightOffset.value)
                }

                return Velocity(x = 0f, y = consume)
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
//                return Offset(x = available.x, y = available.y)
                return Offset.Zero
            }

            override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
                if (heightOffset.value < -appBarHeightPx / 2) {
                    heightOffset.animateTo(-appBarHeightPx)
                    dragCount = -appBarHeightPx
                } else {
                    heightOffset.animateTo(0f)
                    dragCount = 0f
                }
                flingCount = 0f

                return Velocity.Zero
            }
        }
    }



    Column(
        modifier = Modifier
//        .nestedScroll(scrollBehavior.nestedScrollConnection)
//            .nestedScroll(gridNestedScrollConnection)
    ) {
//        MyTopAppBar(scrollBehavior, topAppBarState)

        CollapseAppBar(appBarHeight, appBarHeightPx, heightOffset)
//            MyTabRow(selected, tabs, pagerState)
        MyScrollableTabRow(selected, tabs, pagerState)
        MyPager(selected, pagerState, tabs, listNestedScrollConnection, gridNestedScrollConnection)
//        LazyColumn(
//            Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(1.2f)
//                .background(Color.White)
//                .padding(horizontal = 10.dp)
//        ) {
//            items(30) {
//                Box(
//                    Modifier
//                        .fillMaxWidth()
//                        .height(100.dp)
//                        .padding(10.dp)
//                        .background(Color.Gray)
//                ) {
//                    Column {
//                        Text(text = "$appBarHeightPx")
//                        Text(text = "heightOffset: ${heightOffset.value}")
//
//                    }
//                }
//            }
//        }

    }
}