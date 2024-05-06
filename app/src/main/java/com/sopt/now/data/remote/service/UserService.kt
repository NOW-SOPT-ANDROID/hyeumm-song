package com.sopt.now.data.remote.service

import com.sopt.now.data.remote.dto.request.RequestChagePwDto
import com.sopt.now.data.remote.dto.response.ResponseChangePwDto
import com.sopt.now.data.remote.dto.response.ResponseUserInfoDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface UserService {
    @GET("/member/info")
    fun userInfo() : Call<ResponseUserInfoDto>

    @PATCH("member/password")
    fun changePassword(
        @Body request : RequestChagePwDto
    ) : Call<ResponseChangePwDto>
}