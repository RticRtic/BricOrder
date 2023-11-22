package com.example.bricorder.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun MainView() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            Navigation(navController = navController)
        }
    ) {
        BottomNavGraph(navController = navController)
    }
}