package com.example.bricorder.components.screens.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bricorder.model.Order
import com.example.bricorder.order_case.util.OrderDirection
import com.example.bricorder.order_case.util.OrderType
import com.example.bricorder.orders.OrdersEvent

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    orderDirection: OrderDirection = OrderDirection.Date(OrderType.Descending),
    onOrderChange: (OrderDirection) -> Unit,
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {

        }
    }
}