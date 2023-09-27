package com.example.mybilibili.pojo

data class Tab(
    val label: String
)

data class Video(
    val uri: String,
    val pic: String,
    val title: String,
    val duration: Int,
    val owner: Owner,
    val stat: Stat,
)

data class Owner(
    val name: String,
    val face: String,
)

data class Stat(
    val view: Int,
    val like: Int,
    val danmuku: Int,
)