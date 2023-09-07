package com.example.bricorder.components.screens

sealed class View(val route: String) {
    object OrderScreen : View("orders_screen")
    object AddEditOrderScreen : View("add_edit_order_screen")
}
