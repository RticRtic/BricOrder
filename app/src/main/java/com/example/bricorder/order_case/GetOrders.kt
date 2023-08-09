package com.example.bricorder.order_case

import com.example.bricorder.local_repo.LocalRepository
import com.example.bricorder.model.Order
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector

class GetOrders(private val repository: LocalRepository): Flow<List<Order>> {
    override suspend fun collect(collector: FlowCollector<List<Order>>) {
        repository.getOrders()
    }
}