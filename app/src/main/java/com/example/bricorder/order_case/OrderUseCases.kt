package com.example.bricorder.order_case

import com.example.bricorder.order_case.util.ToggleShowClientInfo

data class OrderUseCases(
    val getOrders: GetOrders,
    val deleteOrder: DeleteOrder,
    val addOrder: AddOrder,
    val getOrder: GetOrder,
    val toggleOnGoingColor: ToggleOngoingColor,
    val toggleShowClientInfo: ToggleShowClientInfo,
)
