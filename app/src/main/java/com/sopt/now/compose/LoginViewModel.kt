package com.sopt.now.compose

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


data class LoginState(
    val isSuccess: Boolean,
    val message: String
)

class LoginViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }
    val liveData = MutableLiveData<LoginState>()
    var userId : String?= ""
    fun login(request: RequestLoginDto) {
        authService.postLogin(request).enqueue(object : Callback<ResponseLoginDto> {
            override fun onResponse(
                call: Call<ResponseLoginDto>,
                response: Response<ResponseLoginDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseLoginDto? = response.body()
                    userId = response.headers()["location"]
                    liveData.value = LoginState(
                        isSuccess = true,
                        message = "로그인 성공 유저의 ID는 $userId 입니다"
                    )
                    Log.d("Login", "data: $data, userId: $userId")
                } else {
                    val error = response.message()
                    liveData.value = LoginState(
                        isSuccess = false,
                        message = "로그인이 실패했습니다 $error"
                    )
                }
            }

            override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                liveData.value = LoginState(
                    isSuccess = false,
                    message = "서버에러"
                )
            }
        })
    }
}