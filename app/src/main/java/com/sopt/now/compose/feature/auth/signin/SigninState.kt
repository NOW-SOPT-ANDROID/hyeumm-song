package com.sopt.now.compose.feature.auth.signin

data class SigninState(
    val isSuccess: Boolean = false,
    val message: String = "",
    val id: String = "",
    val password: String = ""
)