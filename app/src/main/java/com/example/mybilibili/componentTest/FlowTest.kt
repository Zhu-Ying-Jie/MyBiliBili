package com.example.mybilibili.componentTest

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FlowViewModel : ViewModel() {
    private val input = MutableStateFlow("")
    val title: StateFlow<String> = input

    fun onNameChange(msg: String) {
        input.value = msg
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
private fun FlowExample() {
    val viewModel: FlowViewModel = viewModel()
    val title: String by viewModel.title.collectAsState()
    TextField(value = title, onValueChange = {
        viewModel.onNameChange(it)
    })}
