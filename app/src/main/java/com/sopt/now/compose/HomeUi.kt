package com.sopt.now.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeUi() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn {
            items(userList) {
                UserProfileItem(it)
            }
            items(friendList) {
                FriendProfileItem(it)
            }
        }
    }
}
val userList = listOf<User>(
    User(
        profileImage = Icons.Filled.Person,
        name = "송혜음",
        selfDescription = "컴포즈 커피 최고"
    )
)

val friendList = listOf<Friend>(
    Friend(
        profileImage = Icons.Filled.Person,
        name = "이의경",
        selfDescription = "다들 빨리 끝내고 뒤풀이 가고 싶지?"
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "우상욱",
        selfDescription = "나보다 안드 잘하는 사람 있으면 나와봐"
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "배지현",
        selfDescription = "표정 풀자 ^^"
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "이의경",
        selfDescription = "다들 빨리 끝내고 뒤풀이 가고 싶지?"
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "우상욱",
        selfDescription = "나보다 안드 잘하는 사람 있으면 나와봐"
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "배지현",
        selfDescription = "표정 풀자 ^^"
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "이의경",
        selfDescription = "다들 빨리 끝내고 뒤풀이 가고 싶지?"
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "우상욱",
        selfDescription = "나보다 안드 잘하는 사람 있으면 나와봐"
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "배지현",
        selfDescription = "표정 풀자 ^^"
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "이의경",
        selfDescription = "다들 빨리 끝내고 뒤풀이 가고 싶지?"
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "우상욱",
        selfDescription = "나보다 안드 잘하는 사람 있으면 나와봐"
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "배지현",
        selfDescription = "표정 풀자 ^^"
    )
)
