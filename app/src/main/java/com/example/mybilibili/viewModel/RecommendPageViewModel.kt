package com.example.mybilibili.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybilibili.network.BiliData
import com.example.mybilibili.network.Item
import com.example.mybilibili.network.service
import com.example.mybilibili.pojo.Video
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendPageViewModel : ViewModel() {

    private var _videos = MutableStateFlow(listOf<Item>())
    val videos: MutableStateFlow<List<Item>> get() = _videos

    init {
        viewModelScope.launch {
            service.listRepos().enqueue(object : Callback<BiliData> {
                override fun onResponse(call: Call<BiliData>, response: Response<BiliData>) {
                    _videos.value = response.body()?.data?.item!!
                }

                override fun onFailure(call: Call<BiliData>, t: Throwable) {
                    println(t)
                }
            })
        }
    }

    fun refresh() {
        service.listRepos().enqueue(object : Callback<BiliData> {
            override fun onResponse(call: Call<BiliData>, response: Response<BiliData>) {
                _videos.value = response.body()?.data?.item!!.shuffled()
            }

            override fun onFailure(call: Call<BiliData>, t: Throwable) {
                println(t)
            }
        })
    }

}