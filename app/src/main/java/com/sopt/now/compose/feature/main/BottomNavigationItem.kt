package com.sopt.now.compose.feature.main

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val icon: ImageVector,
    val label: String,
    val route: String
)