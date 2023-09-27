package com.example.mybilibili.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mybilibili.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MyTopAppBar(scrollBehavior: TopAppBarScrollBehavior, topAppBarState: TopAppBarState) {
    var textFieldValue by remember {
        mutableStateOf("")
    }
    val swipeState = rememberSwipeableState(initialValue = false)



    TopAppBar(
//        modifier = Modifier.graphicsLayer(translationY = topAppBarState.heightOffset / 2),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
//            containerColor = Color.White
        ),
        navigationIcon = {
            Image(
                painter = painterResource(id = R.drawable.avatat),
                contentDescription = "avatar",
                modifier = Modifier
                    .size(50.dp)
                    .padding(8.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Gray, CircleShape)
            )

        },
        scrollBehavior = scrollBehavior,
        title = {
//            Row(
//                Modifier
//                    .fillMaxWidth()
//                    .height(50.dp),
//                horizontalArrangement = Arrangement.SpaceAround,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
            Row {
                Box(modifier = Modifier.width(10.dp))
                MySearchBar()
            }
//            }
        },
        actions = {
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
        })

//    Row (
//        Modifier
//            .fillMaxWidth()
//            .height(60.dp),
//        horizontalArrangement = Arrangement.SpaceAround,
//        verticalAlignment = Alignment.CenterVertically){
//
//        Image(
//            painter = painterResource(id = R.drawable.avatat),
//            contentDescription = "avatar",
//            modifier = Modifier
//                .size(50.dp)
//                .padding(5.dp)
//                .clip(CircleShape)
//                .border(1.dp, Color.Gray, CircleShape))
//
//        MySearchBar()
//
//        Icon(
//            painterResource(id = R.drawable.stadia_controller_24px),
//            contentDescription = "game",
//            Modifier.size(30.dp),
//            tint = Color.DarkGray)
//
//        Icon(
//            painterResource(id = R.drawable.mail_24px),
//            contentDescription = "email",
//            Modifier.size(30.dp),
//            tint = Color.DarkGray)
//
//    }
}
