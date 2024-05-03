package com.sopt.now.data.remote.service

import com.sopt.now.data.remote.dto.request.RequestLoginDto
import com.sopt.now.data.remote.dto.request.RequestSignUpDto
import com.sopt.now.data.remote.dto.response.ResponseLoginDto
import com.sopt.now.data.remote.dto.response.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/join") //http 메소드
    fun signUp(
        @Body request: RequestSignUpDto
    ): Call<ResponseSignUpDto> //비동기->callback
    @POST("member/login")
    fun login(
        @Body request : RequestLoginDto
    ) : Call<ResponseLoginDto>

}