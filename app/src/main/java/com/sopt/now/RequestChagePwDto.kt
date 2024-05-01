package com.sopt.now

import kotlinx.serialization.SerialName

data class RequestChagePwDto (
    @SerialName("previousPassword")
    val previousPassword: String,
    @SerialName("newPassword")
    val newPassword: String,
    @SerialName("newPasswordVerification")
    val newPasswordVerification: String
)