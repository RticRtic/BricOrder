package com.example.bricorder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bricorder.add_edit_order.components.AddEditOrderScreen
import com.example.bricorder.orders.composables.OrderScreen
import com.example.bricorder.components.screens.View
import com.example.bricorder.orders.composables.OrderDetailScreen
import com.example.bricorder.ui.BricOrderTheme
import com.google.firestore.v1.StructuredQuery.Order
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BricOrderTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = View.OrderScreen.route
                    ) {
                        composable(route = View.OrderScreen.route) {
                            OrderScreen(navController = navController)
                        }
                        composable(
                            route = View.AddEditOrderScreen.route + "?orderId={orderId}${"&orderColor={orderColor}"}",
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
                            )
                        ) {
                            val color = it.arguments?.getInt("orderColor") ?: -1
                            AddEditOrderScreen(navController = navController, orderColor = color)

                        }
//                        composable(
//                            route = View.OrderDetailScreen.route + "?order={orderId}",
//                            arguments = listOf(
//                                navArgument(
//                                    name = "orderId"
//                                ) {
//                                    type = NavType.IntType
//                                    defaultValue = -1
//                                },
//                            )
//                        ) {
//                            val orderId = it.arguments?.getInt("orderId") ?: -1
//                            OrderDetailScreen(navController = navController, orderId = orderId)
//                        }
                    }
                }
            }
        }
    }
}


