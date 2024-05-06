package com.sopt.now.presentation.auth.changepassword

data class ChangePasswordState(
    val isSuccess: Boolean,
    val message: String,
    val userId : String? = "",
    val userNick : String? = "",
    val userPhone : String? = ""
)