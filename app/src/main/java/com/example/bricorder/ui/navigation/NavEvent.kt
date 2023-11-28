package com.example.bricorder.ui.navigation

sealed class NavEvent {
    data class Direction(val navDirection: NavDirection): NavEvent()
}
