package com.sopt.now.compose.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.sopt.now.compose.HomeSideEffect
import com.sopt.now.compose.util.UiState

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
    //viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    // 추후에 hilt 뷰모델로 처리가능함 -> 처리완료
) {
    val followerState = viewModel.followerState.collectAsStateWithLifecycle()
    val state = followerState.value
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    LaunchedEffect(viewModel.homeSideEffect, lifecycleOwner) {
        viewModel.homeSideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is HomeSideEffect.SnackBar -> Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    LaunchedEffect(true) { //이 부분을 true로 둘 것이 아니라 state값이 바뀌면 업데이트가 일어나도록 수정해야함 -> 이거 아님. ㅋ 똑같은 뷰모델을 해줘야함
        viewModel.getFollower()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //UiState 적용 후 코드
        when (state) {
            is UiState.Loading ->
                Text(text = "loading...")

            is UiState.Success -> {
                LazyColumn {
                    //여기에 사용자 정보도 함께 출력하는 코드 추가 작성 필요
                    items(state.data.followers) { follower ->
                        UserProfileItem(follower)
                        HorizontalDivider(thickness = 1.dp, color = Color.DarkGray)
                    }
                }
            }

            is UiState.Failure -> {
                Text(text = state.errorMessage)
            }
        }
    }
}
