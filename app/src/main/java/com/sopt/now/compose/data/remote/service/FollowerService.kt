package com.sopt.now.compose.data.remote.service

import com.sopt.now.compose.data.remote.dto.response.ResponseFollowerDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FollowerService {
    @GET("api/users")
    suspend fun getFollower(
        @Query("page") page: Int = 2
    ): Response<ResponseFollowerDto>
}