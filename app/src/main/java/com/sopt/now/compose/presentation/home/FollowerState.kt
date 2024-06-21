package com.sopt.now.compose.presentation.home

import com.sopt.now.compose.data.remote.dto.response.Data

data class FollowerState(
    val isSuccess: Boolean = false,
    val message: String = "",
    val followers: List<Data> = emptyList()
)