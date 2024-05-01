package com.sopt.now

import kotlinx.serialization.SerialName

data class ResponseChangePwDto (
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String
)