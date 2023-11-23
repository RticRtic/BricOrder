package com.example.bricorder.ui.navigation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

val TAG = "!!!"

@HiltViewModel
class NavStateViewModel @Inject constructor(
) : ViewModel() {
    private val _navState = mutableStateOf(NavState())
    val navState: State<NavState> = _navState

    fun onEvent(event: NavEvent) {
        when (event) {
            is NavEvent.Direction -> {
                navigateToScreen(event.navDirection)
            }
        }
    }

    private fun navigateToScreen(navDirection: NavDirection) {
        _navState.value = navState.value.copy(
            navigation = navDirection
        )

        Log.d(TAG, "navigateToScreen: ${navState.value.navigation.navType}")
    }
}