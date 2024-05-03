package com.sopt.now.presentation.main.mypage

data class UserInfoState(
    val isSuccess: Boolean,
    val message: String,
    val userId : String? = "",
    val userNick : String? = "",
    val userPhone : String? = ""
)