package com.example.mybilibili.network

import android.icu.text.IDNA.Info
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


interface BiliService {
    @GET("x/web-interface/wbi/index/top/feed/rcmd")
    fun listRepos(): Call<BiliData>
}

var retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://api.bilibili.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

var service: BiliService = retrofit.create(BiliService::class.java)


fun main() {
    runBlocking {
        coroutineScope {
            val call = service.listRepos()
            call.enqueue(object :Callback<BiliData>{
                override fun onResponse(call: Call<BiliData>, response: Response<BiliData>) {
                    for (item in response.body()?.data?.item!!){
                        println(item.title)
                        println(item.uri)
                        println(item.pic)
                        println(LocalDateTime.now())

                    }
                    return
                }

                override fun onFailure(call: Call<BiliData>, t: Throwable) {
                    println(t)
                }
            })
        }
    }
}