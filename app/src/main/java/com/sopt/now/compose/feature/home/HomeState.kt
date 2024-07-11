package com.sopt.now.compose.feature.home

import com.sopt.now.compose.domain.entity.response.ResponseFollowerEntity
import com.sopt.now.compose.util.UiState

data class HomeState(
    val followerState: UiState<List<ResponseFollowerEntity>> = UiState.Loading
)