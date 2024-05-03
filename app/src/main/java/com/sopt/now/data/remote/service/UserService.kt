package com.sopt.now.data.remote.service

import com.sopt.now.data.remote.dto.response.ResponseUserInfoDto
import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("/member/info")
    fun userInfo() : Call<ResponseUserInfoDto>
}