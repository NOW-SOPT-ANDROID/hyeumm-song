package com.sopt.now.compose.presentation.auth.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.data.remote.dto.request.RequestSigninDto


class SigninViewModel : ViewModel() {
    //private val authService by lazy { ServicePool.authService }
    private val _signinState = MutableLiveData<SigninState>()
    val signinState: LiveData<SigninState>
        get() = _signinState

    fun signin(request: RequestSigninDto) {
        //서버 닫혀서 이 부분 수동으로 로그인 처리함
        _signinState.value = when {
            request.authenticationId != "asd" -> SigninState(
                isSuccess = false,
                message = "아이디 에러"
            )

            request.password != "asd" -> SigninState(
                isSuccess = false,
                message = "비밀번호 에러"
            )

            else -> {
                SigninActivity.prefs.setString(USER_ID, request.authenticationId)
                SigninState(
                    isSuccess = true,
                    message = "로그인 성공"
                )
            }
        }
//        authService.postLogin(request).enqueue(object : Callback<ResponseSigninDto> {
//            override fun onResponse(
//                call: Call<ResponseSigninDto>,
//                response: Response<ResponseSigninDto>,
//            ) {
//                if (response.isSuccessful) {
//                    val data: ResponseSigninDto? = response.body()
//                    userId = response.headers()[LOCATION]
//                    userId?.let { SigninActivity.prefs.setString(USER_ID, it) }
//                    _signinState.value = SigninState(
//                        isSuccess = true,
//                        message = "로그인 성공 유저의 ID는 $userId 입니다"
//                    )
//                    Log.d("Login", "data: $data, userId: $userId")
//                } else {
//                    val error = response.message()
//                    _signinState.value = SigninState(
//                        isSuccess = false,
//                        message = "로그인이 실패했습니다 $error"
//                    )
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseSigninDto>, t: Throwable) {
//                _signinState.value = SigninState(
//                    isSuccess = false,
//                    message = "서버에러"
//                )
//            }
//        }
//        )
    }

    companion object {
        const val USER_ID = "userId"
        const val LOCATION = "location"
    }
}