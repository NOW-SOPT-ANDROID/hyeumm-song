package com.sopt.now.compose.presentation.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.ServicePool
import com.sopt.now.compose.data.remote.dto.response.ResponseFollowerDto
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val followerService by lazy { ServicePool.followerService }
    private val _followerState = MutableLiveData<FollowerState>()
    val followerState: LiveData<FollowerState>
        get() = _followerState

    fun getFollower() {
        viewModelScope.launch {
            runCatching {
                followerService.getFollower()
            }.onSuccess { response: Response<ResponseFollowerDto> ->
                _followerState.value = response.body()?.data?.let {
                    FollowerState(
                        isSuccess = true,
                        message = "팔로워 불러오기 성공",
                        followers = it
                    )
                }
                Log.d("followers",response.body().toString())
            }.onFailure {
                _followerState.value = FollowerState(
                    isSuccess = false,
                    message = "팔로워 불러오기 실패",
                    followers = emptyList()
                )
            }
        }
    }
}
