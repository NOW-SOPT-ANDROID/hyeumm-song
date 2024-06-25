package com.sopt.now.compose.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.compose.R
import com.sopt.now.compose.domain.repository.FollowerRepository
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
                        _homeSideEffect.emit(HomeSideEffect.SnackBar(R.string.follower_success))
                        _followerState.value = response
                    }
                    is UiState.Failure -> {
                        _homeSideEffect.emit(HomeSideEffect.SnackBar(R.string.follower_failed))
                        _followerState.value = response
                    }
                    else -> {
                        _homeSideEffect.emit(HomeSideEffect.SnackBar(R.string.follower_error))
                        _followerState.value = UiState.Failure(R.string.follower_error)
                    }
                }
            }.onFailure {
                _homeSideEffect.emit(HomeSideEffect.SnackBar(R.string.follower_failed))
                _followerState.value = UiState.Failure(R.string.follower_error)
            }
        }
    }
}
