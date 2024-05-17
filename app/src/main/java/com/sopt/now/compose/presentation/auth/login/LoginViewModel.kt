package com.sopt.now.compose.presentation.auth.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.data.remote.dto.request.RequestLoginDto
import com.sopt.now.compose.data.remote.dto.response.ResponseLoginDto
import com.sopt.now.compose.ServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }
    private val _liveData = MutableLiveData<LoginState>()
    val liveData: LiveData<LoginState>
        get() = _liveData
    var userId: String? = ""
    fun login(request: RequestLoginDto) {
        authService.postLogin(request).enqueue(object : Callback<ResponseLoginDto> {
            override fun onResponse(
                call: Call<ResponseLoginDto>,
                response: Response<ResponseLoginDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseLoginDto? = response.body()
                    userId = response.headers()[LOCATION]
                    userId?.let { LoginActivity.prefs.setString(USER_ID, it) }
                    _liveData.value = LoginState(
                        isSuccess = true,
                        message = "로그인 성공 유저의 ID는 $userId 입니다"
                    )
                    Log.d("Login", "data: $data, userId: $userId")
                } else {
                    val error = response.message()
                    _liveData.value = LoginState(
                        isSuccess = false,
                        message = "로그인이 실패했습니다 $error"
                    )
                }
            }

            override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                _liveData.value = LoginState(
                    isSuccess = false,
                    message = "서버에러"
                )
            }
        })
    }
    companion object {
        const val USER_ID = "userId"
        const val LOCATION = "location"
    }
}