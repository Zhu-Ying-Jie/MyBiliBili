package com.example.mybilibili.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.overscroll
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mybilibili.pojo.Tab
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyScrollableTabRow(
    selected: MutableState<Int>,
    tabs: List<Tab>,
    pagerState: PagerState,
) {
    val density = LocalDensity.current

    val scope = rememberCoroutineScope()

    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection{

        }
    }

    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        edgePadding = 0.dp,
        modifier = Modifier.nestedScroll(nestedScrollConnection),
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.customTabIndicatorOffset(
                    currentTabPosition = tabPositions[pagerState.currentPage],
                    tabWidth = tabWidths[pagerState.currentPage]
                )
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            val beSelected = (index == pagerState.currentPage)

            Tab(
                selected = ( beSelected ),
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
                Text(text = tab.label,
                    modifier = Modifier.padding(10.dp),
                    onTextLayout = { textLayoutResult ->
                        tabWidths[index] =
                            with(density) { textLayoutResult.size.width.toDp() }
                    })
            }
        }
    }
}

fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition,
    tabWidth: Dp
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "customTabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val currentTabWidth by animateDpAsState(
        targetValue = tabWidth,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing), label = ""
    )
    val indicatorOffset by animateDpAsState(
        targetValue = ((currentTabPosition.left + currentTabPosition.right - tabWidth) / 2),
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing), label = ""
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
}
//    Column {
//        ConstraintLayout {
//            val (icon, indicator) = createRefs()
//
//            LazyRow(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp),
//                state = state,
//                content = {
//                    tabs.forEachIndexed { index, tab ->
//                        item {
//                            ScrollableTab(tab.label)
//                        }
//                    }
//                })
//
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .height(50.dp)
//                    .constrainAs(icon) {
//                        end.linkTo(parent.end)
//                    }
//            ) {
//                Box(
//                    modifier = Modifier
//                        .width(30.dp)
//                        .height(50.dp)
//                        .background(
//                            brush = Brush.horizontalGradient(
//                                listOf(
//                                    Color.Unspecified,
//                                    Color.White
//                                )
//                            )
//                        )
//                )
//
//                Icon(
//                    imageVector = Icons.Filled.Menu,
//                    contentDescription = "", tint = Color.DarkGray,
//                    modifier = Modifier
//                        .background(Color.White)
//                        .size(30.dp)
//                )
//                Box(
//                    modifier = Modifier
//                        .width(8.dp)
//                        .height(50.dp)
//                        .background(color = Color.White)
//                )
//
//
//            }
//            Box(modifier = Modifier
//                .width(40.dp)
//                .height(3.dp)
//                .background(Color.Red)
//                .constrainAs(indicator) {
//                    bottom.linkTo(parent.bottom)
//                    start.linkTo(parent.start, margin = 10.dp)
//                })
//        }
////        Column {
////            Text(text = "firstVisibleItemIndex: ${state.firstVisibleItemIndex}")
////            Text(text = "firstVisibleItemScrollOffset: ${state.firstVisibleItemScrollOffset}")
////            Text(text = "layoutInfo: ${state.layoutInfo}")
////            Text(text = "interactionSource: ${state.interactionSource}")
////            Text(text = "isScrollInProgress: ${state.isScrollInProgress}")
////        }
//        androidx.compose.material3.ScrollableTabRow(
//            selectedTabIndex = 0,
//            indicator = { tabPositions: List<TabPosition> ->
//                Box(
//                    Modifier
//                        .tabIndicatorOffset(tabPositions[0])
////                        .fillMaxWidth(.5f)
//                        .height(36.dp)
//                        .background(
//                            brush = Brush.verticalGradient(
//                                colors = listOf(
//                                    Color(0XFF00B2FF),
//                                    Color(0XFF00F0FF)
//                                )
//                            ),
//                            shape = RoundedCornerShape(16.dp)
//                        )
//                )
//            },
//            edgePadding = 0.dp) {
//            tabs.forEachIndexed { index, tab ->
//                Tab(selected = true, onClick = { /*TODO*/ }) {
//                    Text(text = tab.label,
//                        modifier = Modifier.padding(vertical = 10.dp))
//                }
//
//            }
//        }
//    }
//
//
//}
//
//@Composable
//fun ScrollableTab(label: String) {
//    var selected = remember {
//        mutableStateOf(false)
//    }
//    Box (modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp),
//        contentAlignment = Alignment.Center){
//        if (selected.value){
//            Text(text = label, color = Color.Black, fontSize = 20.sp)
//        }else{
//            Text(text = label, color = Color.Gray, fontSize = 20.sp)
//        }
//    }
//}