package com.sopt.now.compose.feature.auth.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.data.remote.dto.request.RequestSigninDto


class SigninViewModel : ViewModel() {
    //private val _signinState = MutableLiveData<SigninState>()
    private val _signinState: MutableLiveData<SigninState> = MutableLiveData(SigninState()) //화면에 들어올때마다 토스트가 뜬다.
    val signinState: LiveData<SigninState>
        get() = _signinState

    fun fetchId(id: String) {
        _signinState.value = _signinState.value?.copy(id = id)
    }

    fun fetchPassword(password: String) {
        _signinState.value = _signinState.value?.copy(password = password)
    }

    fun signin(request: RequestSigninDto) {
        //서버 닫혀서 이 부분 수동으로 로그인 처리함
        _signinState.value = when {
            request.authenticationId != "asd" -> _signinState.value?.copy(
                isSuccess = false,
                message = "아이디 에러"
            )

            request.password != "asd" -> _signinState.value?.copy(
                isSuccess = false,
                message = "비밀번호 에러"
            )

            else -> {
                SigninActivity.prefs.setString(USER_ID, request.authenticationId)
                _signinState.value?.copy(
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