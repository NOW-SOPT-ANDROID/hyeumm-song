package com.sopt.now

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


data class FollowerState(
    val isSuccess: Boolean,
    val message: String
)

class FollowerViewModel : ViewModel() {
    private val followerService by lazy { ServicePool.followerService }
    val liveData = MutableLiveData<FollowerState>()

    fun followerInfo(page:Int) {
        followerService.followerInfo(page).enqueue(object : Callback<ResponseFollowerDto> {
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