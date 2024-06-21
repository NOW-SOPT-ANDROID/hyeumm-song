package com.sopt.now.compose.presentation.main.home

import com.sopt.now.compose.data.remote.dto.response.Data

data class FollowerState(
    val isSuccess: Boolean,
    val message: String,
    val followers: List<Data>
)