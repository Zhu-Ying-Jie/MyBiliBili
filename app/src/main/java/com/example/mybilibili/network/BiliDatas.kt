package com.example.mybilibili.network

data class BiliData (
    val code: Long,
    val message: String,
    val ttl: Long,
    val data: Data
)

data class Data (
    val item: List<Item>,
    val sideBarColumn: List<SideBarColumn>,
    val businessCard: Any? = null,
    val floorInfo: Any? = null,
    val userFeature: Any? = null,
    val preloadExposePct: Double,
    val preloadFloorExposePct: Double,
    val mid: Long
)

data class Item (
    val id: Long,
    val bvid: String,
    val cid: Long,
    val goto: Goto,
    val uri: String,
    val pic: String,
    val pic4_3: String,
    val title: String,
    val duration: Long,
    val pubdate: Long,
    val owner: Owner,
    val stat: Stat,
    val avFeature: Any? = null,
    val isFollowed: Long,
    val rcmdReason: RcmdReason,
    val showInfo: Long,
    val trackID: TrackID,
    val pos: Long,
    val roomInfo: Any? = null,
    val ogvInfo: Any? = null,
    val businessInfo: Any? = null,
    val isStock: Long,
    val enableVT: Long,
    val vtDisplay: String
)

enum class Goto {
    AV
}

data class Owner (
    val mid: Long,
    val name: String,
    val face: String
)

data class RcmdReason (
    val content: String? = null,
    val reasonType: Long
)

data class Stat (
    val view: Long,
    val like: Long,
    val danmaku: Long,
    val vt: Long
)

enum class TrackID {
    WebPegasus5RouterWebPegasus1275944K4Djc1695643379673122
}

data class SideBarColumn (
    val id: Long,
    val goto: String,
    val trackID: String,
    val pos: Long,
    val cardType: String,
    val cardTypeEn: String,
    val cover: String,
    val url: String,
    val title: String,
    val subTitle: String,
    val duration: Long,
    val stats: Stats,
    val roomInfo: Any? = null,
    val newEp: NewEp,
    val styles: List<String>,
    val comic: Any? = null,
    val producer: List<Producer>,
    val source: String,
    val avFeature: Any? = null,
    val isRec: Long,
    val isFinish: Long,
    val isStarted: Long,
    val isPlay: Long,
    val horizontalCover16_9: String,
    val horizontalCover16_10: String,
    val enableVT: Long,
    val vtDisplay: String
)

data class NewEp (
    val id: Long,
    val indexShow: String,
    val cover: String,
    val title: String,
    val longTitle: String,
    val pubTime: String,
    val duration: Long,
    val dayOfWeek: Long
)

data class Producer (
    val mid: Long,
    val name: String,
    val type: Long,
    val isContribute: Long
)

data class Stats (
    val follow: Long,
    val view: Long,
    val danmaku: Long,
    val reply: Long,
    val coin: Long,
    val seriesFollow: Long,
    val seriesView: Long,
    val likes: Long,
    val favorite: Long
)