package com.example.bricorder.orders

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
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
    private val orderUseCases: OrderUseCases,

    ) : ViewModel() {
    private val _state = mutableStateOf(OrdersState())
    val state: State<OrdersState> = _state

    private var recentlyDeletedOrder: Order? = null
    private var getOrdersJob: Job? = null

    init {
        getOrders(OrderDirection.Date(OrderType.Descending))
    }

    fun onEvent(event: OrdersEvent) {
        when (event) {
            is OrdersEvent.Direction -> {
                if (state.value.orderDirection::class == event.orderDirection::class && state.value.orderDirection.orderType == event.orderDirection.orderType) {
                    return
                }
                getOrders(event.orderDirection)
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
                    recentlyDeletedOrder = null
                }
            }
            is OrdersEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
            is OrdersEvent.ToggleOnGoingColor -> {
                _state.value = state.value.copy(
                    isOnGoing = !state.value.isOnGoing

                )
                viewModelScope.launch {
                    event.order.id?.let { orderUseCases.toggleOnGoingColor(it, state.value.isOnGoing) }
                }
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