package com.example.bricorder.add_edit_order.components

data class OrderTextFieldState(
    val title: String = "",
    val description: String = "",

    val isTitleValid: Boolean = true,
    val isHintVisible: Boolean = true,
)

data class Janne(
    val name: String = "",
    val address: String = "",
    val phone: String = "",
    val email: String = "",
    val id: Int? = null,

    val description: String = "",

    val isTitleValid: Boolean = true,
    val isHintVisible: Boolean = true,
)