package com.example.bricorder.order_case

data class OrderUseCases(
    val getOrders: GetOrders,
    val deleteOrder: DeleteOrder,
    val addOrder: AddOrder,
    val getOrder: GetOrder,
)
