package com.sopt.now.compose

import androidx.compose.ui.graphics.vector.ImageVector

data class Friend(
    val profileImage: ImageVector,
    val name: String,
    val selfDescription: String,
)