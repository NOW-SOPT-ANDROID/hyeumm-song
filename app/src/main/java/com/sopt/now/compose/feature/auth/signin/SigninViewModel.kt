package com.sopt.now.compose.feature.auth.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.data.remote.dto.request.RequestSigninDto


class SigninViewModel : ViewModel() {
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
    }

    companion object {
        const val USER_ID = "userId"
    }
}