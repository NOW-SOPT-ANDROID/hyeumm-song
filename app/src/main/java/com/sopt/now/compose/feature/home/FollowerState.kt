package com.sopt.now.compose.feature.home

import com.sopt.now.compose.domain.entity.response.ResponseFollowerEntity

data class FollowerState(
    val isSuccess: Boolean = false,
    val message: String = "",
    val followers: List<ResponseFollowerEntity> = emptyList()
)