package com.sopt.now.compose

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/join") //http 메소드
    fun postSignUp(
        @Body request: RequestSignUpDto
    ): Call<ResponseSignUpDto> //비동기->callback

    @POST("member/login")
    fun postLogin(
        @Body request : RequestLoginDto
    ) : Call<ResponseLoginDto>
}