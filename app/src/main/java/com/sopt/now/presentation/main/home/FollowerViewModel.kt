package com.sopt.now.presentation.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.ServicePool
import kotlinx.coroutines.launch
import retrofit2.HttpException

class FollowerViewModel : ViewModel() {
    private val followerService by lazy { ServicePool.followerService }
    private val _liveData = MutableLiveData<FollowerState>()
    val liveData : LiveData<FollowerState>
        get()=_liveData

    fun followerInfo(page:Int) {
        viewModelScope.launch {
            try {
                _liveData.value = FollowerState(
                    isSuccess = true,
                    message = "팔로워 정보 불러오기에 성공했습니다.",
                )
            } catch (e: HttpException) {
                _liveData.value = FollowerState(
                    isSuccess = false,
                    message = "팔로워 정보 불러오기에 실패했습니다. ${e.code()}"
                )
            }
        }
    }
}