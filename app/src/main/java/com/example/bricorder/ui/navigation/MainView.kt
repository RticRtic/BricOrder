package com.example.bricorder.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun MainView(
    navStateViewModel: NavStateViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val navState = navStateViewModel.navState.value
    Scaffold(
        bottomBar = {
            Navigation(
                navController = navController, currentScreen = navState.navigation,
            onScreenChange = {navStateViewModel.onEvent(NavEvent.Direction(it))})
        }
    ) {
        BottomNavGraph(navController = navController)
    }
}