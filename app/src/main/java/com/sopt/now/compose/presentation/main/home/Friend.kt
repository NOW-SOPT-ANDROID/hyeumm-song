package com.sopt.now.compose.presentation.main.home

import androidx.compose.ui.graphics.vector.ImageVector

data class Friend(
    val profileImage: ImageVector,
    val name: String,
    val selfDescription: String,
)