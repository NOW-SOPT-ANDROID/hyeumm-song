package com.sopt.now.compose.data.remote.service

import com.sopt.now.compose.data.remote.dto.request.RequestSigninDto
import com.sopt.now.compose.data.remote.dto.request.RequestSignUpDto
import com.sopt.now.compose.data.remote.dto.response.ResponseSigninDto
import com.sopt.now.compose.data.remote.dto.response.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    //suspend로 수정 후 올리겠습니다..!
    @POST("member/join") //http 메소드
    fun postSignUp(
        @Body request: RequestSignUpDto
    ): Call<ResponseSignUpDto> //비동기->callback

    @POST("member/signin")
    fun postLogin(
        @Body request : RequestSigninDto
    ) : Call<ResponseSigninDto>
}