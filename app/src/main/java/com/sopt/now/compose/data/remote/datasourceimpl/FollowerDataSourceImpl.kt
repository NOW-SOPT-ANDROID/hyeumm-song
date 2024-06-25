package com.sopt.now.compose.data.remote.datasourceimpl

import com.sopt.now.compose.data.remote.datasource.FollowerDataSource
import com.sopt.now.compose.data.remote.dto.response.ResponseFollowerDto
import com.sopt.now.compose.data.remote.service.FollowerService
import retrofit2.Response
import javax.inject.Inject

class FollowerDataSourceImpl @Inject constructor(
    private val followerService : FollowerService
) : FollowerDataSource {
    override suspend fun getFollower(): Response<ResponseFollowerDto> {
        return followerService.getFollower()
    }
}