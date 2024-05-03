package com.sopt.now

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {
    @GET("/member/info")
    fun userInfo(
        @Header("memberId") userId : Int
    ) : Call<ResponseUserInfoDto>
}