package com.sopt.now.compose.feature.home

import com.sopt.now.compose.R
import com.sopt.now.compose.domain.entity.response.ResponseFollowerEntity

data class HomeState(
    val isSuccess: Boolean = false,
    val message: Int = R.string.follower_failed,
    val followers: List<ResponseFollowerEntity> = emptyList()
)