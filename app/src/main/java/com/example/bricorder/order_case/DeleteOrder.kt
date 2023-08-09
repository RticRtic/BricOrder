package com.example.bricorder.order_case

import com.example.bricorder.local_repo.LocalRepository
import com.example.bricorder.model.Order

class DeleteOrder(private val repository: LocalRepository) {

    suspend fun invoke(order: Order) {
        repository.delete(order)
    }
}