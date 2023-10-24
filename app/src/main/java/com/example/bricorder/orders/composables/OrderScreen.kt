package com.example.bricorder.orders.composables

import OrderItem
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.example.bricorder.orders.OrdersViewModel
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bricorder.components.screens.View
import com.example.bricorder.components.screens.composables.OrderSection
import com.example.bricorder.orders.OrdersEvent
import kotlinx.coroutines.launch

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
                    navController.navigate(View.AddEditOrderScreen.route)
                },
                backgroundColor = MaterialTheme.colors.background
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Order"
                )
            }
        },
        scaffoldState = scaffoldState

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Orders",
                    style = MaterialTheme.typography.h4
                )
                IconButton(
                    onClick = {
                        viewModel.onEvent(OrdersEvent.ToggleOrderSection)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Sort"
                    )
                }
            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    orderDirection = state.orderDirection,
                    onOrderChange = {
                        viewModel.onEvent(OrdersEvent.Direction(it))
                    }

                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.orders) { order ->
                    OrderItem(
                        order = order,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
//                                navController.navigate(
//                                    View.OrderDetailScreen.route + "?orderId=${order.id}&orderTitle=${order.title}&orderDescription=${order.description}&orderColor=${order.color}"
//
//                                )
                                navController.navigate(
                                    View.AddEditOrderScreen.route +
                                            "?orderId=${order.id}" +
                                            "&orderTitle=${order.title}" +
                                            "&orderDescription=${order.description}" +
                                            "&orderColor=${order.color}" +
                                            "&clientName=${order.client?.name}" +
                                            "&clientAddress=${order.client?.address}" +
                                            "&clientEmail=${order.client?.email}" +
                                            "&clientPhone=${order.client?.phone}"
                                )
                            },
                        onDelete = {
                            viewModel.onEvent(OrdersEvent.Delete(order))
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Order ${order.title} deleted",
                                    actionLabel = "Undo"
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(OrdersEvent.RestoreOrder)
                                }
                            }
                        },
                        onGoing = {
                            viewModel.onEvent(OrdersEvent.ToggleOnGoingColor(order))
                            order.onGoing = viewModel.state.value.isOnGoing
                        },
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}


