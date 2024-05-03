package com.sopt.now

import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("/member/info")
    fun userInfo() : Call<ResponseUserInfoDto>
}