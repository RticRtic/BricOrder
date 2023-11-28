package com.example.bricorder.ui.navigation

sealed class NavDirection(val navType: NavType) {

    class Screen(navType: NavType): NavDirection(navType)
//    class OrderScreen(navType: NavType): NavDirection(navType.toString())
//    class AddAndEditScreen(navType: NavType): NavDirection(navType.toString())


    fun copy(navType: NavType): NavDirection {
        return when(this) {
            is Screen -> Screen(navType)
        }
    }

}
