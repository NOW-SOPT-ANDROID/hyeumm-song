package com.sopt.now

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


data class UserInfoState(
    val isSuccess: Boolean,
    val message: String,
    val userId : String? = "",
    val userNick : String? = "",
    val userPhone : String? = ""
)

class UserInfoViewModel : ViewModel() {
    private val userService by lazy { ServicePool.userService }
    val liveData = MutableLiveData<UserInfoState>()

    fun userInfo() {
        userService.userInfo().enqueue(object : Callback<ResponseUserInfoDto> {
            override fun onResponse(
                call: Call<ResponseUserInfoDto>,
                response: Response<ResponseUserInfoDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseUserInfoDto? = response.body()
                    liveData.value = UserInfoState(
                        isSuccess = true,
                        message = "회원 정보 불러오기에 성공했습니다.",
                        userId = data?.data?.authenticationId,
                        userNick = data?.data?.nickname,
                        userPhone = data?.data?.phone
                    )
                    Log.d("UserInfo", "data: $data")
                } else {
                    val error = response.message()
                    liveData.value = UserInfoState(
                        isSuccess = false,
                        message = "회원 정보 불러오기에 실패했습니다.  $error"
                    )
                }
            }

            override fun onFailure(call: Call<ResponseUserInfoDto>, t: Throwable) {
                liveData.value = UserInfoState(
                    isSuccess = false,
                    message = "서버에러"
                )
            }
        })
    }
}