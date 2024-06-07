package com.sopt.now.data.remote.service

import com.sopt.now.data.remote.dto.request.RequestChagePasswordDto
import com.sopt.now.data.remote.dto.request.RequestLoginDto
import com.sopt.now.data.remote.dto.request.RequestSignUpDto
import com.sopt.now.data.remote.dto.response.ResponseChangePasswordDto
import com.sopt.now.data.remote.dto.response.ResponseLoginDto
import com.sopt.now.data.remote.dto.response.ResponseSignUpDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST

interface AuthService {
    //suspend로 구현 후 다시 올리겠습니다...!
    @POST("member/join") //http 메소드
    fun postSignUp(
        @Body request: RequestSignUpDto
    ): Call<ResponseSignUpDto> //비동기->callback
    @POST("member/login")
    suspend fun postLogin(
        @Body request : RequestLoginDto
    ) : Response<ResponseLoginDto>

}