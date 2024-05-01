package com.sopt.now

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/join") //http 메소드
    fun signUp(
        @Body request: RequestSignUpDto,
    ): Call<ResponseSignUpDto> //비동기->callback
}