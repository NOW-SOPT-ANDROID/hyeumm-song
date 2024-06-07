package com.sopt.now.data.remote.service

import com.sopt.now.data.remote.dto.response.ResponseFollowerDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FollwerService {
    @GET("/api/users")
    suspend fun getFollowerInfo(
       @Query ("page") page : Int
    ) : Response<ResponseFollowerDto>
}