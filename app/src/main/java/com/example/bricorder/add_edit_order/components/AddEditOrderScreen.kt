package com.example.bricorder.add_edit_order.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bricorder.R
import com.example.bricorder.model.Order
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable

fun AddEditOrderScreen(
    navController: NavController,
    orderColor: Int,
    viewModel: AddEditOrderViewModel = hiltViewModel()
) {
    val titleState = viewModel.orderTitle.value
    val orderDescriptionState = viewModel.orderDescription.value
    val orderMarkState = viewModel.orderMark.value
    val clientStateName = viewModel.clientName.value
    val clientStateAddress = viewModel.clientAddress.value
    val clientStatePhone = viewModel.clientPhone.value
    val clientStateEmail = viewModel.clientEmail.value
    val silverWhite = colorResource(id = R.color.silver_white)

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditOrderViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is AddEditOrderViewModel.UiEvent.SaveOrder -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditOrderEvent.SaveOrder)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Save note")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(silverWhite)
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Order.orderColors.forEach { color ->
                    val colorInt = color.toArgb()
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(15.dp, CircleShape)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = 3.dp,
                                color = if (viewModel.orderColor.value == colorInt) {
                                    Color.Black
                                } else Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable {
                                viewModel.onEvent(AddEditOrderEvent.ChangeColor(colorInt))
                            }
                    )
                }
            }
            Text("Create New Order", style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.height(16.dp))
            AddEditOrderProjectCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor =
                if (viewModel.startColor.value != Color(viewModel.orderColor.value)) {
                    Color(viewModel.orderColor.value)
                } else viewModel.startColor.value,

                titleText = titleState.title,
                titleHint = titleState.description,
                onValueChangeTitle = { viewModel.onEvent(AddEditOrderEvent.EnteredTitle(it)) },
                onFocusChangeTitle = { viewModel.onEvent(AddEditOrderEvent.ChangeTitleFocus(it)) },
                isHintVisibleTitle = titleState.isHintVisible,

                descriptionText = orderDescriptionState.title,
                descriptionHint = orderDescriptionState.description,
                onValueChangeDescription = {
                    viewModel.onEvent(
                        AddEditOrderEvent.EnteredDescription(
                            it
                        )
                    )
                },
                onFocusChangeDescription = {
                    viewModel.onEvent(
                        AddEditOrderEvent.ChangeDescriptionFocus(
                            it
                        )
                    )
                },
                isHintVisibleDescription = orderDescriptionState.isHintVisible,

                orderNumberText = orderMarkState.title,
                orderNumberHint = orderMarkState.description,
                onValueChangeOrderNumber = { viewModel.onEvent(AddEditOrderEvent.EnteredMarking(it)) },
                onFocusChangeOrderNumber = {
                    viewModel.onEvent(
                        AddEditOrderEvent.ChangeMarkingFocus(
                            it
                        )
                    )
                },
                isHintVisibleOrderNumber = orderMarkState.isHintVisible,
            )
            Spacer(modifier = Modifier.height(36.dp))
            AddEditOrderClientCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color.LightGray,
                clientText = clientStateName.title,
                clientHint = clientStateName.description,
                onValueChangeClient = { viewModel.onEvent(AddEditOrderEvent.EnteredClientName(it)) },
                onFocusChangeClient = { viewModel.onEvent(AddEditOrderEvent.ChangeClientNameFocus(it)) },
                isHintVisibleClient = clientStateName.isHintVisible,
                clientAddressText = clientStateAddress.title,
                clientAddressHint = clientStateAddress.description,
                onValueChangeClientAddress = {
                    viewModel.onEvent(
                        AddEditOrderEvent.EnteredClientAddress(
                            it
                        )
                    )
                },
                onFocusChangeClientAddress = {
                    viewModel.onEvent(
                        AddEditOrderEvent.ChangeClientAddressFocus(
                            it
                        )
                    )
                },
                isHintVisibleClientAddress = clientStateAddress.isHintVisible,
                clientEmailText = clientStateEmail.title,
                clientEmailHint = clientStateEmail.description,
                onValueChangeClientEmail = {
                    viewModel.onEvent(
                        AddEditOrderEvent.EnteredClientEmail(
                            it
                        )
                    )
                },
                onFocusChangeClientEmail = {
                    viewModel.onEvent(
                        AddEditOrderEvent.ChangeClientEmailFocus(
                            it
                        )
                    )
                },
                isHintVisibleClientEmail = clientStateEmail.isHintVisible,
                clientPhoneText = clientStatePhone.title,
                clientPhoneHint = clientStatePhone.description,
                onValueChangeClientPhone = {
                    viewModel.onEvent(
                        AddEditOrderEvent.EnteredClientPhone(
                            it
                        )
                    )
                },
                onFocusChangeClientPhone = {
                    viewModel.onEvent(
                        AddEditOrderEvent.ChangeClientPhoneFocus(
                            it
                        )
                    )
                },
                isHintVisibleClientPhone = clientStatePhone.isHintVisible,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Add Employee", style = MaterialTheme.typography.h4)
        }
    }
}
