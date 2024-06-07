package com.sopt.now.presentation.main.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.ServicePool
import kotlinx.coroutines.launch
import retrofit2.HttpException

class UserInfoViewModel : ViewModel() {
    private val userService by lazy { ServicePool.userService }
    private val _liveData = MutableLiveData<UserInfoState>()
    val liveData : LiveData<UserInfoState>
        get()=_liveData

    init{
        userInfo()
    }

    fun userInfo() {
        viewModelScope.launch {
            try {
                val response = userService.getUserInfo()
                val data = response.body()?.data
                _liveData.value = UserInfoState(
                    isSuccess = true,
                    message = "회원 정보 불러오기에 성공했습니다.",
                    userId = data?.authenticationId,
                    userNickname = data?.nickname,
                    userPhone = data?.phone
                )
            } catch (e: HttpException) {
                _liveData.value = UserInfoState(
                    isSuccess = false,
                    message = "회원 정보 불러오기에 실패했습니다. ${e.code()}"
                )
            }
        }
    }
}