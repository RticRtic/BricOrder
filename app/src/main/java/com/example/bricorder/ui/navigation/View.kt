package com.example.bricorder.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class View(
    var title: String,
    var icon: ImageVector,
    val route: NavDirection,
) {
    object OrderScreen : View("Orders", Icons.Default.Home, route = NavDirection.Screen(NavType.OrderScreen))
    object AddEditOrderScreen : View("AddAndEditScreen", Icons.Default.Add, route = NavDirection.Screen(NavType.AddAndEditScreen))

}
