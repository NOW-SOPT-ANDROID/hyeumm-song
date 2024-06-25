package com.sopt.now.compose.feature.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.di.ServicePool
import com.sopt.now.compose.data.remote.dto.response.ResponseUserInfoDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserInfoViewModel : ViewModel() {
    private val userService by lazy { ServicePool.userService }
    private val _userInfoState = MutableLiveData<UserInfoState>()
    val userInfoState: LiveData<UserInfoState>
        get() = _userInfoState

    fun userInfo(userId: Int) {
        userService.getUserInfo().enqueue(object : Callback<ResponseUserInfoDto> {
            override fun onResponse(
                call: Call<ResponseUserInfoDto>,
                response: Response<ResponseUserInfoDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseUserInfoDto? = response.body()
                    _userInfoState.value = UserInfoState(
                        isSuccess = true,
                        message = "회원 정보 불러오기에 성공했습니다.",
                        userId = data?.data?.authenticationId,
                        userNickname = data?.data?.nickname,
                        userPhone = data?.data?.phone
                    )
                    Log.d("UserInfo", "data: $data, userId: $userId")
                } else {
                    val error = response.message()
                    _userInfoState.value = UserInfoState(
                        isSuccess = false,
                        message = "회원 정보 불러오기에 실패했습니다.  $error"
                    )
                }
            }

            override fun onFailure(call: Call<ResponseUserInfoDto>, t: Throwable) {
                _userInfoState.value = UserInfoState(
                    isSuccess = false,
                    message = "서버에러"
                )
            }
        })
    }
}