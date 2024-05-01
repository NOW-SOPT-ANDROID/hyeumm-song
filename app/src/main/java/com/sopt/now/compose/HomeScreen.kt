package com.sopt.now.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeUi(viewModel: HomeViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn {
            items(viewModel.userList) {
                UserProfileItem(it)
                HorizontalDivider(thickness = 1.dp, color = Color.DarkGray)
            }
            items(viewModel.friendList) {
                FriendProfileItem(it.profileImage,it.name,it.selfDescription)
            }
        }
    }
}
