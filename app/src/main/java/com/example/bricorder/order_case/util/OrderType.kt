package com.example.bricorder.order_case.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
