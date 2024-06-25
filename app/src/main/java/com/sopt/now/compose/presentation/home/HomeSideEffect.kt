package com.sopt.now.compose.presentation.home

sealed class HomeSideEffect {
    //data class SnackBar(val message: Int) : HomeSideEffect()
    data class SnackBar(val message: String) : HomeSideEffect() //잠깐 문자열 하드코딩하느라 바꿈
}