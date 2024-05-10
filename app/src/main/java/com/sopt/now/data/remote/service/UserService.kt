package com.sopt.now.data.remote.service

import com.sopt.now.data.remote.dto.request.RequestChagePasswordDto
import com.sopt.now.data.remote.dto.response.ResponseChangePasswordDto
import com.sopt.now.data.remote.dto.response.ResponseUserInfoDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface UserService {
    @GET("/member/info")
    fun getUserInfo() : Call<ResponseUserInfoDto>

    @PATCH("member/password")
    fun patchChangePassword(
        @Body request : RequestChagePasswordDto
    ) : Call<ResponseChangePasswordDto>
}