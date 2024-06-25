package com.sopt.now.compose.feature.auth.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.data.remote.dto.request.RequestSignUpDto
import com.sopt.now.compose.data.remote.dto.response.ResponseSignUpDto
import com.sopt.now.compose.di.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }
    private val _signUpState = MutableLiveData<SignUpState>()
    val signUpState: LiveData<SignUpState>
        get() = _signUpState

    fun signUp(request: RequestSignUpDto) {
        authService.postSignUp(request).enqueue(object : Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseSignUpDto? = response.body()
                    val userId = response.headers()[LOCATION]
                    _signUpState.value = SignUpState(
                        isSuccess = true,
                        message = "회원가입 성공 유저의 ID는 $userId 입니다"
                    )
                    Log.d("SignUp", "data: $data, userId: $userId")
                } else {
                    val error = response.message()
                    _signUpState.value = SignUpState(
                        isSuccess = false,
                        message = "회원가입이 실패했습니다 $error"
                    )
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                _signUpState.value = SignUpState(
                    isSuccess = false,
                    message = "서버에러"
                )
            }
        })
    }
    companion object {
        const val LOCATION = "location"
    }
}