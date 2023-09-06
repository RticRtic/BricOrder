package com.example.bricorder.order_case

import com.example.bricorder.local_repo.LocalRepository
import com.example.bricorder.model.InvalidOrderException
import com.example.bricorder.model.Order

class AddOrder(private val repository: LocalRepository) {
        suspend operator fun invoke(order: Order) {
            if (order.title.isBlank()) {
                throw InvalidOrderException("The title of the order can't be empty.")
            }
            if (order.description.isBlank()) {
                throw InvalidOrderException("The description of the order can't be empty.")
            }
            repository.insertOrder(order)
        }
}
