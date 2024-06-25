package com.sopt.now.compose.data.remote.repositoryimpl

import com.sopt.now.compose.util.UiState
import com.sopt.now.compose.data.remote.datasource.FollowerDataSource
import com.sopt.now.compose.domain.repository.FollowerRepository
import com.sopt.now.compose.feature.home.FollowerState
import javax.inject.Inject

class FollowerRepositoryImpl @Inject constructor(
    private val followerRemoteDataSource: FollowerDataSource
) : FollowerRepository {
    override suspend fun getFollower(): UiState<FollowerState> {
        val response = followerRemoteDataSource.getFollower()
        if(response.isSuccessful) {
            response.body()?.let { result ->
                val followerState = FollowerState(
                    isSuccess = true,
                    message = "Success",
                    followers = result.data
                )
                return UiState.Success(followerState)
            }
        }
        return UiState.Failure(response.message().toInt()) // errorMessage를 int로 설정해서 위와같이 설정함. 약간 애매하다
    }
}