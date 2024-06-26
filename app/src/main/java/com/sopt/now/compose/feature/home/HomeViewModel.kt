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
    // StateFlow
    private val _homeState = MutableStateFlow<UiState<HomeState>>(UiState.Loading)
    val homeState: StateFlow<UiState<HomeState>>
        get() = _homeState.asStateFlow()

    // SharedFlow
    private val _homeSideEffect = MutableSharedFlow<HomeSideEffect>()
    val homeSideEffect: SharedFlow<HomeSideEffect>
        get() = _homeSideEffect.asSharedFlow()

    fun getFollower() {
        viewModelScope.launch {
            runCatching {
                repository.getFollower()
            }.onSuccess { followers ->
                _homeState.value = UiState.Success(
                    HomeState(
                        isSuccess = true,
                        message = R.string.follower_success,
                        followers = followers
                    )
                )
                _homeSideEffect.emit(HomeSideEffect.SnackBar(R.string.follower_success))
            }.onFailure {
                _homeState.value = UiState.Failure(R.string.follower_failed)
                _homeSideEffect.emit(HomeSideEffect.SnackBar(R.string.follower_failed))
            }
        }
    }
}
