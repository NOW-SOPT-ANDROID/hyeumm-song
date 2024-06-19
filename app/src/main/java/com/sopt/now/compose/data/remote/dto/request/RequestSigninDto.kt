package com.sopt.now.compose.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class RequestSigninDto(
    @SerialName("authenticationId")
    val authenticationId: String,
    @SerialName("password")
    val password: String
)