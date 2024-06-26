package com.sopt.now.compose.data.remote.repositoryimpl

import com.sopt.now.compose.data.remote.datasource.FollowerDataSource
import com.sopt.now.compose.data.remote.dto.response.toResponseUserList
import com.sopt.now.compose.domain.entity.response.ResponseFollowerEntity
import com.sopt.now.compose.domain.repository.FollowerRepository
import javax.inject.Inject

class FollowerRepositoryImpl @Inject constructor(
    private val followerRemoteDataSource: FollowerDataSource
) : FollowerRepository {
    override suspend fun getFollower(): List<ResponseFollowerEntity> {
        val response = followerRemoteDataSource.getFollower()
        if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                return responseBody.toResponseUserList()
            }
        }
        return emptyList()
    }
}