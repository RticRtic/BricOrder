package com.example.bricorder.order_case

import com.example.bricorder.local_repo.LocalRepository
import com.example.bricorder.model.Order
import com.example.bricorder.orders.OrdersEvent

class DeleteOrder(private val repository: LocalRepository) {

    suspend operator fun invoke(order: Order) {
        repository.delete(order)
    }
}