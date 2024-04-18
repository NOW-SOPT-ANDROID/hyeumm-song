package com.sopt.now.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                HorizontalDivider(thickness = 1.dp, color = Color.DarkGray)
            }
            items(friendList) {
                FriendProfileItem(it)
            }
        }
    }
}
val userList = listOf<User>(
    User(
        profileImage = R.drawable.cute,
        name = "송혜음",
        selfDescription = "여러분 보고 시퍼yo 훌쩍훌쩍"
    )
)

val friendList = listOf<Friend>(
    Friend(
        profileImage = Icons.Filled.Person,
        name = "박동민",
        selfDescription = "곽의진...얼굴 재치 실력 모든걸 다 가진 남자... 하지만 밀양박씨 36대손인 나 박동민은 가지지 못했지"
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "이석준",
        selfDescription = "죄송합니다 저 도핑했습니다... 안드-로이더 \uD83D\uDC89"
    ),
    Friend(
        profileImage = Icons.Filled.Person,
        name = "박유진",
        selfDescription = "(ง˙∇˙)ว 에라 모르겠다"
    ),Friend(
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
