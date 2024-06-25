package com.sopt.now.compose.data.remote.datasource

import com.sopt.now.compose.data.remote.dto.response.ResponseFollowerDto
import retrofit2.Response

interface FollowerDataSource {
    suspend fun getFollower() : Response<ResponseFollowerDto>
}