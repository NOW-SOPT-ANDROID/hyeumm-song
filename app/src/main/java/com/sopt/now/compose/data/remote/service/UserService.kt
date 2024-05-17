package com.sopt.now.compose.data.remote.service

import com.sopt.now.compose.data.remote.dto.response.ResponseUserInfoDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {
    @GET("/member/info")
    fun getUserInfo() : Call<ResponseUserInfoDto>
}