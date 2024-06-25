package com.sopt.now.compose.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.domain.repository.FollowerRepository
import com.sopt.now.compose.HomeSideEffect
import com.sopt.now.compose.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: FollowerRepository
) : ViewModel() {
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
                repository.getFollower()
            }.onSuccess { response ->
                when (response) {
                    is UiState.Success -> {
                        _homeSideEffect.emit(HomeSideEffect.SnackBar("팔로워를 성공적으로 불러왔습니다."))
                        _followerState.value = response
                    }
                    is UiState.Failure -> {
                        _homeSideEffect.emit(HomeSideEffect.SnackBar("팔로워를 불러오지 못했습니다."))
                        _followerState.value = response
                    }
                    else -> {
                        _homeSideEffect.emit(HomeSideEffect.SnackBar("알 수 없는 오류가 발생했습니다."))
                        _followerState.value = UiState.Failure("알 수 없는 오류가 발생했습니다.")
                    }
                }
            }.onFailure {
                _homeSideEffect.emit(HomeSideEffect.SnackBar("팔로워를 불러오지 못했습니다."))
                _followerState.value = UiState.Failure(
                    errorMessage = "팔로워 불러오기 실패"
                )
            }
        }
    }
}
