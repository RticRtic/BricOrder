package com.example.bricorder.order_case.util

sealed class OrderDirection(val orderType: OrderType) {
    class Title(orderType: OrderType): OrderDirection(orderType)
    class Date(orderType: OrderType): OrderDirection(orderType)
    class Color(orderType: OrderType): OrderDirection(orderType)
    class OnGoing(orderType: OrderType): OrderDirection(orderType)

    fun copy(orderType: OrderType): OrderDirection {
        return when(this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
            is OnGoing -> OnGoing(orderType)
        }
    }
}
