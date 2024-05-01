package com.sopt.now

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    val homeListData = listOf(
        HomeList(
            profileImage = R.drawable.main,
            name = "송혜음",
            selfDescription = "멀티 뷰 리싸이클러뷰!",
            HomeList.VIEW_TYPE_USER
        ),
        HomeList(
            profileImage = R.drawable.main,
            name = "박동민",
            selfDescription = "곽의진...얼굴 재치 실력 모든걸 다 가진 남자... 하지만 밀양박씨 36대손인 나 박동민은 가지지 못했지",
            HomeList.VIEW_TYPE_FRIEND
        ),
        HomeList(
            profileImage = R.drawable.main,
            name = "이석준",
            selfDescription = "죄송합니다 저 도핑했습니다... 안드-로이더 \uD83D\uDC89",
            HomeList.VIEW_TYPE_FRIEND
        ),
        HomeList(
            profileImage = R.drawable.main,
            name = "박유진",
            selfDescription = "(ง˙∇˙)ว 에라 모르겠다",
            HomeList.VIEW_TYPE_FRIEND
        ),
        HomeList(
            profileImage = R.drawable.main,
            name = "이의경",
            selfDescription = "다들 빨리 끝내고 뒤풀이 가고 싶지? ㅎㅎ 아직 반도 안왔어 ^&^",
            HomeList.VIEW_TYPE_FRIEND
        ),
        HomeList(
            profileImage = R.drawable.main,
            name = "우상욱",
            selfDescription = "나보다 안드 잘하는 사람 있으면 나와봐",
            HomeList.VIEW_TYPE_FRIEND
        ),
        HomeList(
            profileImage = R.drawable.main,
            name = "배지현",
            selfDescription = "표정 풀자 ^^",
            HomeList.VIEW_TYPE_FRIEND
        ),
        HomeList(
            profileImage = R.drawable.main,
            name = "이의경",
            selfDescription = "다들 빨리 끝내고 뒤풀이 가고 싶지? ㅎㅎ 아직 반도 안왔어 ^&^",
            HomeList.VIEW_TYPE_FRIEND
        ),
        HomeList(
            profileImage = R.drawable.main,
            name = "우상욱",
            selfDescription = "나보다 안드 잘하는 사람 있으면 나와봐",
            HomeList.VIEW_TYPE_FRIEND
        ),
        HomeList(
            profileImage = R.drawable.main,
            name = "배지현",
            selfDescription = "표정 풀자 ^^",
            HomeList.VIEW_TYPE_FRIEND
        ),
        HomeList(
            profileImage = R.drawable.main,
            name = "이의경",
            selfDescription = "다들 빨리 끝내고 뒤풀이 가고 싶지? ㅎㅎ 아직 반도 안왔어 ^&^",
            HomeList.VIEW_TYPE_FRIEND
        ),
        HomeList(
            profileImage = R.drawable.main,
            name = "우상욱",
            selfDescription = "나보다 안드 잘하는 사람 있으면 나와봐",
            HomeList.VIEW_TYPE_FRIEND
        ),
        HomeList(
            profileImage = R.drawable.main,
            name = "배지현",
            selfDescription = "표정 풀자 ^^",
            HomeList.VIEW_TYPE_FRIEND
        )
    )
}