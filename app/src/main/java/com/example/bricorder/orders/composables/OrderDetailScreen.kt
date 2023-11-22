package com.example.bricorder.orders.composables

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun OrderDetailScreen(
    navController: NavController,
    orderId: Int,
) {
   Text(text = "Order Detail Screen")

}