package com.example.bricorder.order_case

import com.example.bricorder.local_repo.LocalRepository
import com.example.bricorder.model.Order

class GetOrder(private val repository: LocalRepository) {

        suspend fun invoke(id: Int): Order? {
            return repository.getOrderById(id)
        }
}