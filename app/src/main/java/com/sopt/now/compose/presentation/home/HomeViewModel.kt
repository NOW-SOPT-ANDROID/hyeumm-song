package com.sopt.now.compose.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.HomeSideEffect
import com.sopt.now.compose.UiState
import com.sopt.now.compose.data.remote.ServicePool
import com.sopt.now.compose.data.remote.dto.response.ResponseFollowerDto
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val followerService by lazy { ServicePool.followerService }
    //stateFlow
    private val _followerState = MutableStateFlow<UiState<FollowerState>>(UiState.Loading)
    val followerState: StateFlow<UiState<FollowerState>>
        get() = _followerState.asStateFlow()

    //sharedFlow
    private val _homeSideEffect = MutableSharedFlow<HomeSideEffect>()
    val homeSideEffect: SharedFlow<HomeSideEffect>
        get() = _homeSideEffect.asSharedFlow()

    fun getFollower() {
        viewModelScope.launch {
            runCatching {
                followerService.getFollower()
            }.onSuccess { response: Response<ResponseFollowerDto> ->
                _homeSideEffect.emit(HomeSideEffect.SnackBar("팔로워를 성공적으로 불러왔습니다."))
                _followerState.value = response.body()?.data?.let {
                    UiState.Success(
                        FollowerState(
                            isSuccess = true,
                            message = "팔로워 불러오기 성공",
                            followers = it
                        )
                    )
                } ?: UiState.Failure(
                    errorMessage = "팔로워 목록이 비어 있습니다"
                )
            }.onFailure {
                _homeSideEffect.emit(HomeSideEffect.SnackBar("팔로워를 불러오지 못했습니다."))
                _followerState.value = UiState.Failure(
                    errorMessage = "팔로워 불러오기 실패"
                )
            }
        }
    }
}
