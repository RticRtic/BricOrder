package com.example.bricorder.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bricorder.add_edit_order.components.AddEditOrderScreen
import com.example.bricorder.orders.composables.OrderScreen

@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = View.OrderScreen.route) {
        composable(View.OrderScreen.route) {
            OrderScreen(navController = navController)
        }
        composable(
            View.AddEditOrderScreen.route + "?orderId={orderId}${"&orderColor={orderColor}"}",
            arguments = listOf(
                navArgument(
                    name = "orderId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "orderColor"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
            )) {
            val color = it.arguments?.getInt("orderColor") ?: -1
            AddEditOrderScreen(navController = navController, orderColor = color)
        }
    }

}