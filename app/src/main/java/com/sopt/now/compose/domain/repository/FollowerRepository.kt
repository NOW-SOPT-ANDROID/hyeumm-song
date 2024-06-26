package com.sopt.now.compose.domain.repository

import com.sopt.now.compose.domain.entity.response.ResponseFollowerEntity

interface FollowerRepository {
    suspend fun getFollower() : List<ResponseFollowerEntity>
}
