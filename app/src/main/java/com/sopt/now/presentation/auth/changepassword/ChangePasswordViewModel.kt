package com.sopt.now.presentation.auth.changepassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.ServicePool
import com.sopt.now.data.remote.dto.request.RequestChagePasswordDto
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ChangePasswordViewModel  : ViewModel() {
    private val userService by lazy { ServicePool.userService }
    val liveData = MutableLiveData<ChangePasswordState>()

    fun changePassword(request : RequestChagePasswordDto) {
        viewModelScope.launch {
            try {
                liveData.value = ChangePasswordState(
                    isSuccess = true,
                    message = "비밀번호가 성공적으로 변경되었습니다. 재로그인이 필요합니다."
                )
            } catch (e: HttpException) {
                liveData.value = ChangePasswordState(
                    isSuccess = false,
                    message = "비밀번호 변경에 실패했습니다. ${e.code()}"
                )
            }
        }
    }
}