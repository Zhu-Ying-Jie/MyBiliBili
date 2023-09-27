package com.example.mybilibili.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mybilibili.R


@Composable
fun CollapseAppBar(appBarHeight: Dp, appBarHeightPx: Float, heightOffset: Animatable<Float, AnimationVector1D>) {

    val scale = appBarHeightPx / appBarHeight.value

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .height(
                appBarHeight
                        + (heightOffset.value / scale).dp
            )
            .fillMaxWidth()
            .padding(10.dp)
//            .graphicsLayer(translationY = heightOffset.value)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .requiredHeight(appBarHeight)
                .fillMaxWidth()
                .padding(10.dp)) {
            Image(
                painter = painterResource(id = R.drawable.avatat),
                contentDescription = "avatar",
                modifier = Modifier

                    .clip(CircleShape)
                    .border(1.dp, Color.Gray, CircleShape)
            )
            Box(modifier = Modifier.width(10.dp))
            MySearchBar()
            Box(modifier = Modifier.width(10.dp))
            Icon(
                painterResource(id = R.drawable.stadia_controller_24px),
                contentDescription = "game",
                Modifier.size(30.dp),
                tint = Color.DarkGray
            )
            Box(modifier = Modifier.width(5.dp))
            Icon(
                painterResource(id = R.drawable.mail_24px),
                contentDescription = "email",
                Modifier.size(30.dp),
                tint = Color.DarkGray
            )
        }

    }
}
