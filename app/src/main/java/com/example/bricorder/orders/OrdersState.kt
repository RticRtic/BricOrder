package com.example.bricorder.orders


import com.example.bricorder.model.Order
import com.example.bricorder.order_case.util.OrderDirection
import com.example.bricorder.order_case.util.OrderType

data class OrdersState(
    val orders: List<Order> = emptyList(),
    val orderDirection: OrderDirection = OrderDirection.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false,
    val isOnGoing: Boolean = true,
)
