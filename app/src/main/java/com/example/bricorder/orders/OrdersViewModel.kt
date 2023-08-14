package com.example.bricorder.orders

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bricorder.model.Order
import com.example.bricorder.order_case.OrderUseCases
import com.example.bricorder.order_case.util.OrderDirection
import com.example.bricorder.order_case.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val orderUseCases: OrderUseCases

) : ViewModel() {
    private val _state = mutableStateOf(OrdersState())
    val state: State<OrdersState> = _state

    private var recentlyDeletedOrder: Order? = null
    private var getOrdersJob: Job? = null

    init {
        getOrders(OrderDirection.Date(OrderType.Descending))
    }

    fun onEvent(event: OrdersEvent) {
        when(event) {
            is OrdersEvent.OrderDirection -> {
                if (state.value.orderDirection::class == state.value.orderDirection::class && state.value.orderDirection.orderType == state.value.orderDirection.orderType) {
                    return
                }
                getOrders(state.value.orderDirection)
            }
            is OrdersEvent.Delete -> {
                viewModelScope.launch {
                    orderUseCases.deleteOrder(event.order)
                    recentlyDeletedOrder = event.order
                }
            }

            is OrdersEvent.RestoreOrder -> {
                viewModelScope.launch {
                    orderUseCases.addOrder(recentlyDeletedOrder ?: return@launch)
                }
                recentlyDeletedOrder = null
            }

            else -> {
                return
            }
        }

    }


    private fun getOrders(orderDirection: OrderDirection) {
        getOrdersJob?.cancel()
        getOrdersJob = orderUseCases.getOrders(orderDirection).onEach { orders ->
            _state.value = state.value.copy(
                orders = orders,
                orderDirection = orderDirection
            )
        }.launchIn(viewModelScope)
    }
}