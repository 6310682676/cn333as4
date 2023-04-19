package com.example.cn333as4.ui

data class AppUiState (
    val heightInvalid: Boolean = false,
    val widthInvalid: Boolean = false,
    val url: String = "",
    val inputAllValid: Boolean = false,
    val enterHeight: Boolean = false,
    val enterWidth: Boolean = false,
    val enterCategoryType: Boolean = false

)