package com.example.bricorder.local_repo

import com.example.bricorder.model.Order
import com.example.bricorder.orders.OrdersEvent
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    fun getOrders(): Flow<List<Order>>
    suspend fun getOrderById(id: Int): Order?
    suspend fun insertOrder(order: Order)
    suspend fun delete(order: Order)

    suspend fun updateOngoingColor(id: Int, onGoing: Boolean)
}
