package com.example.bricorder.order_case

import com.example.bricorder.local_repo.LocalRepository
import com.example.bricorder.model.Order
import com.example.bricorder.order_case.util.OrderDirection
import com.example.bricorder.order_case.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.map

class GetOrders(private val repository: LocalRepository) {

    operator fun invoke(orderDirection: OrderDirection = OrderDirection.Date(OrderType.Descending)): Flow<List<Order>> {
        return repository.getOrders().map { orders ->
            when(orderDirection.orderType) {
                is OrderType.Ascending -> {
                    when(orderDirection) {
                        is OrderDirection.Title -> {
                            orders.sortedBy { it.title.lowercase() }
                        }
                        is OrderDirection.Date -> {
                            orders.sortedBy { it.timestamp }
                        }
                        is OrderDirection.Color -> {
                            orders.sortedBy { it.color }
                        }
                    }
                }
                is OrderType.Descending -> {
                    when(orderDirection) {
                        is OrderDirection.Title -> {
                            orders.sortedByDescending { it.title.lowercase() }
                        }
                        is OrderDirection.Date -> {
                            orders.sortedByDescending { it.timestamp }
                        }
                        is OrderDirection.Color -> {
                            orders.sortedByDescending { it.color }
                        }
                    }
                }
            }
        }
    }
}