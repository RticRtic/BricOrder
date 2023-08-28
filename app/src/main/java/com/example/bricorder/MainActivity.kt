package com.example.bricorder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bricorder.components.screens.OrderScreen
import com.example.bricorder.components.screens.View
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                Text(text = "Hello World!")
            }
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = View.OrderScreen.route) {
                composable(route = View.OrderScreen.route) {
                    OrderScreen(navController = navController)
                }
//                composable(
//                    route = View.AddEditOrderScreen.route +
//                            "?orderId={orderId}&orderName={orderName}",
//                    arguments = listOf(
//                        navArgument(
//                            name = "orderId") {
//                            defaultValue = -1
//                            type = NavType.IntType
//                        },
//                        navArgument("orderName") {
//                            defaultValue = ""
//                            type = NavType.StringType
//                        }
//                    )
//                ) {
//                    val orderId = it.arguments?.getInt("orderId") ?: -1
//                    val orderName = it.arguments?.getString("orderName") ?: ""
//                    //AddEditOrderScreen(navController = navController, orderId = orderId, orderName = orderName)
//                }

            }
        }
    }
}


