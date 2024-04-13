package com.sopt.now

import androidx.annotation.DrawableRes

data class Friend(
    @DrawableRes val profileImage: Int,
    val name: String,
    val selfDescription: String,
    val viewType : Int
) {
    companion object {
        const val VIEW_TYPE_USER = 0
        const val VIEW_TYPE_FRIEND = 1
    }
}