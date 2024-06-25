package com.sopt.now.compose.domain.repository

import com.sopt.now.compose.util.UiState
import com.sopt.now.compose.feature.home.FollowerState

interface FollowerRepository {
    suspend fun getFollower() : UiState<FollowerState>
}