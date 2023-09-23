package com.example.bricorder.components.screens.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bricorder.components.screens.composables.DefaultRadioButton
import com.example.bricorder.order_case.util.OrderDirection
import com.example.bricorder.order_case.util.OrderType

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
            DefaultRadioButton(
                text = "Date",
                selected = orderDirection is OrderDirection.Date,
                onSelect = {
                    onOrderChange(OrderDirection.Date(orderDirection.orderType))
                }
            )
            DefaultRadioButton(
                text = "Color",
                selected = orderDirection is OrderDirection.Color,
                onSelect = {
                    onOrderChange(OrderDirection.Color(orderDirection.orderType))
                }
            )

            DefaultRadioButton(
                text = "On Going",
                selected = orderDirection is OrderDirection.OnGoing,
                onSelect = {
                    onOrderChange(OrderDirection.OnGoing(orderDirection.orderType))
                }
            )
            Spacer(modifier = Modifier.width(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                DefaultRadioButton(
                    text = "Top",
                    selected = orderDirection.orderType is OrderType.Ascending,
                    onSelect = {
                        onOrderChange(orderDirection.copy(OrderType.Ascending))
                    }
                )
                Spacer(modifier = Modifier.width(2.dp))
                DefaultRadioButton(
                    text = "Bottom",
                    selected = orderDirection.orderType is OrderType.Descending,
                    onSelect = {
                        onOrderChange(orderDirection.copy(OrderType.Descending))
                    }
                )
            }
        }
    }
}