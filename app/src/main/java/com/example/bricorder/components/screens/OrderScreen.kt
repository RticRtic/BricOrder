package com.example.bricorder.components.screens

import android.annotation.SuppressLint
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.example.bricorder.orders.OrdersViewModel
import androidx.compose.material.rememberScaffoldState
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun OrderScreen(
    navController: NavController,
    viewModel: OrdersViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Order"
                )
            }
        },
        scaffoldState = scaffoldState,
        snackbarHost = {
            scaffoldState.snackbarHostState
        }
    ) {

    }

}