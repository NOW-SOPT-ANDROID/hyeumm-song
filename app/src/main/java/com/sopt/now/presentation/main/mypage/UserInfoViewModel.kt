package com.sopt.now.presentation.main.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.ServicePool
import com.sopt.now.data.remote.dto.response.ResponseUserInfoDto
import com.sopt.now.data.remote.dto.response.UserInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserInfoViewModel : ViewModel() {
    private val userService by lazy { ServicePool.userService }
    private val _liveData = MutableLiveData<UserInfoState>()
    val liveData : LiveData<UserInfoState>
        get()=_liveData

    init{
        userInfo()
    }

    fun userInfo() {
        userService.getUserInfo().enqueue(object : Callback<ResponseUserInfoDto> {
            override fun onResponse(
                call: Call<ResponseUserInfoDto>,
                response: Response<ResponseUserInfoDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseUserInfoDto? = response.body()
                    _liveData.value = UserInfoState(
                        isSuccess = true,
                        message = "회원 정보 불러오기에 성공했습니다.",
                        userId = data?.data?.authenticationId,
                        userNick = data?.data?.nickname,
                        userPhone = data?.data?.phone
                    )
                } else {
                    val error = response.message()
                    _liveData.value = UserInfoState(
                        isSuccess = false,
                        message = "회원 정보 불러오기에 실패했습니다.  $error"
                    )
                }
            }

            override fun onFailure(call: Call<ResponseUserInfoDto>, t: Throwable) {
                _liveData.value = UserInfoState(
                    isSuccess = false,
                    message = "서버에러"
                )
            }
        })
    }
}