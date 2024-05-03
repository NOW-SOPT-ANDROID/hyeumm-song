package com.sopt.now.data.remote.dto.request

import kotlinx.serialization.SerialName

data class RequestChagePwDto (
    @SerialName("previousPassword")
    val previousPassword: String,
    @SerialName("newPassword")
    val newPassword: String,
    @SerialName("newPasswordVerification")
    val newPasswordVerification: String
)