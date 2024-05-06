package com.sopt.now.data.remote.service

import com.sopt.now.data.remote.dto.response.ResponseFollowerDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FollwerService {
    @GET("/api/users")
    fun getFollowerInfo(
       @Query ("page") page : Int
    ) : Call<ResponseFollowerDto>
}