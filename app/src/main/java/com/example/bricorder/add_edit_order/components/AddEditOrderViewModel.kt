package com.example.bricorder.add_edit_order.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bricorder.model.Client
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
) : ViewModel() {

    private val _orderTitle = mutableStateOf(
        OrderTextFieldState(
            description = "What's the Project Name?"
        )
    )
    private val _orderDescription = mutableStateOf(
        OrderTextFieldState(
            description = "Add Description"
        )
    )

    private val _orderMark = mutableStateOf(
        OrderTextFieldState(
            description = "Order-Number"
        )
    )

    private val _selectedColor = mutableStateOf(Color.White)

    private val _clientName = mutableStateOf(
        OrderTextFieldState(
            description = "Client Name"
        )
    )

    private val _clientAddress = mutableStateOf(
        OrderTextFieldState(
            description = "Client Address"
        )
    )

    private val _clientPhone = mutableStateOf(
        OrderTextFieldState(
            description = "Client Phone"
        )
    )

    private val _clientEmail = mutableStateOf(
        OrderTextFieldState(
            description = "Client Email"
        )
    )
    val orderTitle: State<OrderTextFieldState> = _orderTitle
    val orderDescription: State<OrderTextFieldState> = _orderDescription
    val orderMark: State<OrderTextFieldState> = _orderMark
    val startColor: State<Color> = _selectedColor
    val clientName: State<OrderTextFieldState> = _clientName
    val clientAddress: State<OrderTextFieldState> = _clientAddress
    val clientPhone: State<OrderTextFieldState> = _clientPhone
    val clientEmail: State<OrderTextFieldState> = _clientEmail

    private val _orderColor = mutableIntStateOf(_selectedColor.value.toArgb())
    val orderColor: State<Int> = _orderColor

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentOrderId: Int? = null
    private var updatedClient: Client? = null
    private var currentClientId: Int? = null


    init {
        savedStateHandle.get<Int>("orderId")?.let { orderId ->
            if (orderId != -1) {
                viewModelScope.launch {
                    orderUseCases.getOrder(orderId)?.also { order ->
                        currentOrderId = order.id
                        currentClientId = order.client?.id

                        _orderTitle.value = orderTitle.value.copy(
                            title = order.title,
                            isHintVisible = false
                        )
                        _orderDescription.value = orderDescription.value.copy(
                            title = order.description,
                            isHintVisible = false
                        )
                        _orderMark.value = orderMark.value.copy(
                            title = order.orderMark,
                            isHintVisible = false
                        )
                        _clientName.value = clientName.value.copy(
                            title = order.client?.name ?: "",
                            isHintVisible = false
                        )
                        _clientAddress.value = clientAddress.value.copy(
                            title = order.client?.address ?: "",
                            isHintVisible = false
                        )
                        _clientPhone.value = clientPhone.value.copy(
                            title = order.client?.phone ?: "",
                            isHintVisible = false
                        )
                        _clientEmail.value = clientEmail.value.copy(
                            title = order.client?.email ?: "",
                            isHintVisible = false
                        )

                        _orderColor.intValue = order.color

                    }
                }
            }
        }
    }


    fun onEvent(event: AddEditOrderEvent) {
        when (event) {
            is AddEditOrderEvent.EnteredTitle -> {
                _orderTitle.value = orderTitle.value.copy(
                    title = event.value
                )
            }

            is AddEditOrderEvent.ChangeTitleFocus -> {
                _orderTitle.value = orderTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && orderTitle.value.title.isBlank()
                )
            }

            is AddEditOrderEvent.EnteredDescription -> {
                _orderDescription.value = orderDescription.value.copy(
                    title = event.value
                )
            }

            is AddEditOrderEvent.ChangeDescriptionFocus -> {
                _orderDescription.value = orderDescription.value.copy(
                    isHintVisible = !event.focusState.isFocused && orderDescription.value.title.isBlank()
                )
            }

            is AddEditOrderEvent.EnteredMarking -> {
                _orderMark.value = orderMark.value.copy(
                    title = event.value
                )
            }

            is AddEditOrderEvent.ChangeMarkingFocus -> {
                _orderMark.value = orderMark.value.copy(
                    isHintVisible = !event.focusState.isFocused && orderMark.value.title.isBlank()
                )
            }

            is AddEditOrderEvent.EnteredClientName -> {
                _clientName.value = clientName.value.copy(
                    title = event.value
                )
            }

            is AddEditOrderEvent.EnteredClientAddress -> {
                _clientAddress.value = clientAddress.value.copy(
                    title = event.value
                )
            }

            is AddEditOrderEvent.EnteredClientPhone -> {
                _clientPhone.value = clientPhone.value.copy(
                    title = event.value
                )
            }

            is AddEditOrderEvent.EnteredClientEmail -> {
                _clientEmail.value = clientEmail.value.copy(
                    title = event.value
                )
            }

            is AddEditOrderEvent.ChangeClientNameFocus -> {
                _clientName.value = clientName.value.copy(
                    isHintVisible = !event.focusState.isFocused && clientName.value.title.isBlank()
                )
            }

            is AddEditOrderEvent.ChangeClientAddressFocus -> {
                _clientAddress.value = clientAddress.value.copy(
                    isHintVisible = !event.focusState.isFocused && clientAddress.value.title.isBlank()
                )
            }

            is AddEditOrderEvent.ChangeClientPhoneFocus -> {
                _clientPhone.value = clientPhone.value.copy(
                    isHintVisible = !event.focusState.isFocused && clientPhone.value.title.isBlank()
                )
            }

            is AddEditOrderEvent.ChangeClientEmailFocus -> {
                _clientEmail.value = clientEmail.value.copy(
                    isHintVisible = !event.focusState.isFocused && clientEmail.value.title.isBlank()
                )
            }

            is AddEditOrderEvent.ChangeColor -> {
                _orderColor.intValue = event.color
            }

            is AddEditOrderEvent.SaveOrder -> {
                viewModelScope.launch {
                    try {
                        updatedClient = Client(
                            name = clientName.value.title,
                            address = clientAddress.value.title,
                            phone = clientPhone.value.title,
                            email = clientEmail.value.title,
                            id = currentClientId
                        )
                        orderUseCases.addOrder(
                            Order(
                                id = currentOrderId,
                                title = orderTitle.value.title,
                                description = orderDescription.value.title,
                                orderMark = orderMark.value.title,
                                timestamp = System.currentTimeMillis(),
                                color = orderColor.value,
                                onGoing = true,
                                client = updatedClient,
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
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveOrder : UiEvent()
    }
}