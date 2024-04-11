package com.sopt.now

import androidx.annotation.DrawableRes

data class User(
    @DrawableRes val profileImage: Int,
    val name: String,
    val selfDescription: String
)
data class Friend(
    @DrawableRes val profileImage: Int,
    val name: String,
    val selfDescription: String,
)