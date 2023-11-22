package com.example.bricorder.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class View(
    var title: String,
    var icon: ImageVector,
    val route: String
) {
    object OrderScreen : View("Orders", Icons.Default.Home, "orders_screen")
    object AddEditOrderScreen : View("AddAndEditScreen", Icons.Default.Add, "add_edit_order_screen")

}
