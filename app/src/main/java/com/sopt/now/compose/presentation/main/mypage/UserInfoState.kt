package com.sopt.now.compose.presentation.main.mypage

data class UserInfoState(
    val isSuccess: Boolean,
    val message: String,
    val userId : String? = "",
    val userNickname : String? = "",
    val userPhone : String? = ""
)