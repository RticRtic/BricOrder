package com.example.bricorder.orders


import com.example.bricorder.model.Order
import com.example.bricorder.order_case.util.OrderDirection

sealed class OrdersEvent {
    data class Direction(val orderDirection: OrderDirection): OrdersEvent()
    data class Delete(val order: Order): OrdersEvent()
    data class ToggleOnGoingColor(val order: Order): OrdersEvent()
    object RestoreOrder: OrdersEvent()
    object ToggleOrderSection: OrdersEvent()

}
