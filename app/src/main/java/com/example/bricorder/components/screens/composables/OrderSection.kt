package com.example.bricorder.components.screens.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
            DefaultRadioButton(
                text = "Title",
                selected = orderDirection is OrderDirection.Title,
                onSelect = {
                    onOrderChange(OrderDirection.Title(orderDirection.orderType))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Date",
                selected = orderDirection is OrderDirection.Date,
                onSelect = {
                    onOrderChange(OrderDirection.Date(orderDirection.orderType))
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            DefaultRadioButton(
                text = "Color",
                selected = orderDirection is OrderDirection.Color,
                onSelect = {
                    onOrderChange(OrderDirection.Color(orderDirection.orderType))
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                DefaultRadioButton(
                    text = "Ascending",
                    selected = orderDirection.orderType is OrderType.Ascending,
                    onSelect = {
                        onOrderChange(orderDirection.copy(OrderType.Ascending))
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                DefaultRadioButton(
                    text = "Descending",
                    selected = orderDirection.orderType is OrderType.Descending,
                    onSelect = {
                        onOrderChange(orderDirection.copy(OrderType.Descending))
                    }
                )
            }
        }
    }
}