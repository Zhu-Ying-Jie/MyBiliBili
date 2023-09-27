package com.example.mybilibili.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mybilibili.components.RecommendGrid
import com.example.mybilibili.viewModel.RecommendPageViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.wait

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview(showBackground = true)
fun RecommendPage() {
    val viewModel: RecommendPageViewModel = viewModel()
    val videos = viewModel.videos.collectAsState()


    val scope = rememberCoroutineScope()
    val refresh = remember {
        mutableStateOf(false)
    }
    val state = rememberPullRefreshState(refreshing = refresh.value, onRefresh = {
        scope.launch {
            refresh.value = true
//            delay(1000)
            viewModel.refresh()
            delay(500)
            refresh.value = false
        }
    })
    Box(
        Modifier
            .pullRefresh(state = state, enabled = true)
            .fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Box(modifier = Modifier.height(8.dp))
            RecommendGrid(videos)

        }
        PullRefreshIndicator(
            refresh.value,
            state,
            modifier = Modifier.align(TopCenter)
        )
    }
}