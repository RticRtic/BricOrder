package com.example.bricorder.local_repo

import com.example.bricorder.model.Client
import com.example.bricorder.model.Order
import kotlinx.coroutines.flow.Flow

class LocalRepositoryImpl(
    private val orderDao: OrderDao
): LocalRepository {
    override fun getOrders(): Flow<List<Order>> {
        return orderDao.getOrders()
    }

    override suspend fun getOrderById(id: Int): Order? {
        return orderDao.getOrderById(id)
    }

    override suspend fun insertOrder(order: Order) {
        orderDao.insertOrder(order)
    }

    override suspend fun delete(order: Order) {
        orderDao.delete(order)
    }

    override suspend fun updateOngoingColor(id: Int, onGoing: Boolean) {
        return orderDao.onGoingColor(id, onGoing)
    }
}