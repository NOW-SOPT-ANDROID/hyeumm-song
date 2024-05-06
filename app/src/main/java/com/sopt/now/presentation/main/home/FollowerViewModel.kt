package com.sopt.now.presentation.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.ServicePool
import com.sopt.now.data.remote.dto.response.ResponseFollowerDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel : ViewModel() {
    private val followerService by lazy { ServicePool.followerService }
    val liveData = MutableLiveData<FollowerState>()

    fun followerInfo(page:Int) {
        followerService.getFollowerInfo(page).enqueue(object : Callback<ResponseFollowerDto> {
            override fun onResponse(
                call: Call<ResponseFollowerDto>,
                response: Response<ResponseFollowerDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseFollowerDto? = response.body()
                    liveData.value = FollowerState(
                        isSuccess = true,
                        message = "팔로워 정보 불러오기에 성공했습니다.",
                    )
                } else {
                    val error = response.message()
                    liveData.value = FollowerState(
                        isSuccess = false,
                        message = "팔로워 정보 불러오기에 실패했습니다.  $error"
                    )
                }
            }

            override fun onFailure(call: Call<ResponseFollowerDto>, t: Throwable) {
                liveData.value = FollowerState(
                    isSuccess = false,
                    message = "서버에러"
                )
            }
        })
    }
}