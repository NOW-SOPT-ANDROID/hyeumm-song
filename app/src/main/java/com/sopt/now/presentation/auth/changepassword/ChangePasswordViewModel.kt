package com.sopt.now.presentation.auth.changepassword

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.ServicePool
import com.sopt.now.data.remote.dto.request.RequestChagePwDto
import com.sopt.now.data.remote.dto.response.ResponseChangePwDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordViewModel  : ViewModel() {
    private val userService by lazy { ServicePool.userService }
    val liveData = MutableLiveData<ChangePasswordState>()

    fun changePassword(request : RequestChagePwDto) {
        userService.patchChangePassword(request).enqueue(object : Callback<ResponseChangePwDto> {
            override fun onResponse(
                call: Call<ResponseChangePwDto>,
                response: Response<ResponseChangePwDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseChangePwDto? = response.body()
                    liveData.value = ChangePasswordState(
                        isSuccess = true,
                        message = "비밀번호가 성공적으로 변경되었습니다. 재로그인이 필요합니다."
                    )
                } else {
                    val error = response.message()
                    liveData.value = ChangePasswordState(
                        isSuccess = false,
                        message = "비밀번호 변경에 실패했습니다.  $error"
                    )
                }
            }

            override fun onFailure(call: Call<ResponseChangePwDto>, t: Throwable) {
                liveData.value = ChangePasswordState(
                    isSuccess = false,
                    message = "서버에러"
                )
            }
        })
    }
}