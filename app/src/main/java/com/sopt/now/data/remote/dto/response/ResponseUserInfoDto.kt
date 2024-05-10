package com.sopt.now.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserInfoDto (
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data : UserInfo
)
@Serializable
data class UserInfo (
    @SerialName("authenticationId")
    val authenticationId: String,
    @SerialName("nicknamename")
    val nicknamename: String,
    @SerialName("phone")
    val phone: String
)