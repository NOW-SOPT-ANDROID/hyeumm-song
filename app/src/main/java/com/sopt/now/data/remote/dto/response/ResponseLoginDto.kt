package com.sopt.now.data.remote.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseLoginDto (
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String
)