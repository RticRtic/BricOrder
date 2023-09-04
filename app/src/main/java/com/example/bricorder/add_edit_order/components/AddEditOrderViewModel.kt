package com.example.bricorder.add_edit_order.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bricorder.model.InvalidOrderException
import com.example.bricorder.model.Order
import com.example.bricorder.order_case.OrderUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AddEditOrderViewModel @Inject constructor(
    private val orderUseCases: OrderUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _orderTitle = mutableStateOf(OrderTextFieldState(
        hint = "Enter title ..."
    ))
    val orderTitle: State<OrderTextFieldState> = _orderTitle

    private val _orderDescription = mutableStateOf(OrderTextFieldState(
        hint = "Enter some content"
    ))
    val orderDescription: State<OrderTextFieldState> = _orderDescription

    private val _orderColor = mutableIntStateOf(Order.orderColors.random().toArgb())
    val orderColor: State<Int> = _orderColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentOrderId: Int? = null


    init{
        savedStateHandle.get<Int>("orderId")?.let { orderId ->
            if (orderId != -1) {
                viewModelScope.launch {
                    orderUseCases.getOrder(orderId)?.also { order ->
                        currentOrderId = order.id
                        _orderTitle.value = orderTitle.value.copy(
                            text = order.title,
                            isHintVisible = false
                        )
                        _orderDescription.value = _orderDescription.value.copy(
                            text = order.description,
                            isHintVisible = false
                        )
                        _orderColor.intValue = order.color
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditOrderEvent) {
        when(event) {
            is AddEditOrderEvent.EnteredTitle -> {
                _orderTitle.value = orderTitle.value.copy(
                   text = event.value
                )
            }
            is AddEditOrderEvent.ChangeTitleFocus -> {
                _orderTitle.value = orderTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && orderTitle.value.text.isBlank()
                )
            }
            is AddEditOrderEvent.EnteredDescription -> {
                _orderDescription.value = _orderDescription.value.copy(
                    text = event.value
                )
            }
            is AddEditOrderEvent.ChangeDescriptionFocus -> {
                _orderDescription.value = _orderDescription.value.copy(
                    isHintVisible = !event.focusState.isFocused && orderDescription.value.text.isBlank()
                )
            }
            is AddEditOrderEvent.ChangeColor -> {
                _orderColor.intValue = event.color
            }
            is AddEditOrderEvent.SaveOrder -> {
                viewModelScope.launch {
                    try {
                        orderUseCases.addOrder(
                            Order(
                                title = orderTitle.value.text,
                                description = orderDescription.value.text,
                                timestamp = System.currentTimeMillis(),
                                color = orderColor.value,
                                id = currentOrderId ?: 0
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveOrder)
                    } catch (e: InvalidOrderException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save note"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveOrder: UiEvent()
    }

}