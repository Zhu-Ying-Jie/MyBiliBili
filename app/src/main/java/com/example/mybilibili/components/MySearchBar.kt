package com.example.mybilibili.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview(showBackground = true)
fun MySearchBar(){

    var textFieldValue by remember {
        mutableStateOf("")
    }

    val width = 230.dp

    BasicTextField(
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
        },
//        keyboardOptions = KeyboardOptions(imeAction = androidx.compose.ui.text.input.ImeAction.Search),
        singleLine = true,
        modifier = Modifier.width(width).aspectRatio(6f / 1f),
        decorationBox = { innerTextField ->
            Row(Modifier.fillMaxSize().border(1.dp, Color.Gray, CircleShape)
                .padding(horizontal = 5.dp),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "",
                    Modifier.padding(5.dp),
                    tint = Color.Gray)
                innerTextField()
            }
        },
    )
}