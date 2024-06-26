package com.sopt.now.compose.feature.home

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
import com.sopt.now.compose.util.UiState

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val followerState = viewModel.homeState.collectAsStateWithLifecycle()
    val state = followerState.value
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    LaunchedEffect(viewModel.homeSideEffect, lifecycleOwner) {
        viewModel.homeSideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    is HomeSideEffect.SnackBar -> Toast.makeText(
                        context,
                        sideEffect.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    LaunchedEffect(true) {
        viewModel.getFollower()
    }

    HomeScreen(state)
}

@Composable
fun HomeScreen(
    state: UiState<HomeState>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (state) {
            is UiState.Loading ->
                Text(text = "loading...")

            is UiState.Success -> {
                LazyColumn {
                    items(state.data.followers) { follower ->
                        UserProfileItem(follower)
                        HorizontalDivider(thickness = 1.dp, color = Color.DarkGray)
                    }
                }
            }

            is UiState.Failure -> {
                Text(text = state.errorMessage.toString())
            }
        }
    }
}
