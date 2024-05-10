package com.sopt.now.data.remote.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestSignUpDto(
    @SerialName("authenticationId")
    val authenticationId: String,
    @SerialName("password")
    val password: String,
    @SerialName("nicknamename")
    val nicknamename: String,
    @SerialName("phone")
    val phone: String,
)
