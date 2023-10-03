package com.example.bricorder.orders.composables

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bricorder.components.screens.View
import com.example.bricorder.model.Order

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun OrderDetailScreen(
    navController: NavController,
    orderId: Int,
) {
   Text(text = "Order Detail Screen")

}