package com.example.mybilibili.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mybilibili.network.Item

@Composable
fun VideoCard(item: Item) {
    Column(modifier = Modifier
        .shadow(
            elevation = 2.dp,
            shape = RoundedCornerShape(3)
        )
        .width(250.dp)
        .defaultMinSize(minWidth = 100.dp)
        .aspectRatio(4f / 5f)
        .clip(RoundedCornerShape(3))

    ) {
        Box {
            AsyncImage(
                modifier = Modifier
                    .aspectRatio(4f / 3f)
                    .background(Color.Gray),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.pic)
                    .crossfade(true)
                    .build(),
//                model = "https://i2.hdslb.com/bfs/archive/c30e65d402c2bc0ad76f7fed246eca563c514b34.jpg@672w_378h_1c_!web-home-common-cover.webp",
                contentDescription = "",
                contentScale = ContentScale.FillHeight,
                alignment = Alignment.CenterStart)
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .background(Color.White)
                .fillMaxHeight()
                .padding(horizontal = 8.dp, vertical = 12.dp)) {
            Text(
                //            modifier = Modifier.padding(5.dp),
                fontFamily = FontFamily.Monospace,
                fontSize = 16.sp,
                text = item.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.AccountBox,
                    contentDescription = "",
                    tint = Color.Gray,
                    modifier = Modifier.size(17.dp)
                )
                Box(Modifier.size(5.dp))
                Text(
                    modifier = Modifier,
                    color = Color.Gray,
                    fontSize = 13.sp,

                    text = item.owner.name,
                    //            modifier = Modifier.padding(horizontal = 5.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}