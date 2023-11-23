package com.example.bricorder.ui.navigation

data class NavState(
//    val screens: List<NavType> = emptyList(),
    val navigation: NavDirection = NavDirection.Screen(NavType.OrderScreen),
)
