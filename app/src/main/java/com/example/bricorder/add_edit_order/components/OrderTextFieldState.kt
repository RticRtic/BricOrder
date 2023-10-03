package com.example.bricorder.add_edit_order.components

data class OrderTextFieldState(
    val title: String = "",
    val description: String = "",

    val isTitleValid: Boolean = true,
    val isHintVisible: Boolean = true,
)
