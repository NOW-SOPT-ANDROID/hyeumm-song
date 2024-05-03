package com.sopt.now.compose

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class RequestLoginDto(
    @SerialName("authenticationId")
    val authenticationId: String,
    @SerialName("password")
    val password: String
)