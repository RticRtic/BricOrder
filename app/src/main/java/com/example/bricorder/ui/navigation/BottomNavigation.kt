package com.example.bricorder.ui.navigation

import android.util.Log
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun Navigation(
    navController: NavController,
    currentScreen: NavDirection = NavDirection.Screen(NavType.OrderScreen),
    onScreenChange: (NavDirection) -> Unit,
) {
    val items = listOf(
        View.OrderScreen,
        View.AddEditOrderScreen
    )
    BottomNavigation(
        modifier = Modifier
            .height(50.dp)
            .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)),
        backgroundColor = Color.LightGray,
        contentColor = Color.Black,
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(text = item.title, fontSize = 9.sp) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(alpha = 0.4f),
                alwaysShowLabel = true,
                selected = currentScreen.navType == item.route.navType,
                onClick = {
                    onScreenChange(item.route)
                    navController.navigate(item.route.toString()) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    }
}
