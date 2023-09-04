package com.example.bricorder.add_edit_order.components

data class OrderTextFieldState(
    val text: String = "",
    val hint: String = "",
    val isHintVisible: Boolean = true,
    val isTextValid: Boolean = true,
)
