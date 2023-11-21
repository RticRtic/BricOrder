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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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
                .background(Color.White)
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
            Spacer(modifier = Modifier.height(16.dp))
            AddEditOrderCard(
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
//            TransparentHintTextField(
//                text = titleState.title,
//                hint = titleState.description,
//                onValueChange = {
//                    viewModel.onEvent(AddEditOrderEvent.EnteredTitle(it))
//                },
//                onFocusChange = {
//                    viewModel.onEvent(AddEditOrderEvent.ChangeTitleFocus(it))
//                },
//                isHintVisible = titleState.isHintVisible,
//                singleLine = true,
//                textStyle = MaterialTheme.typography.h5,
//                modifier = Modifier.fillMaxWidth()
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            TransparentHintTextField(
//                text = orderDescriptionState.title,
//                hint = orderDescriptionState.description,
//                onValueChange = {
//                    viewModel.onEvent(AddEditOrderEvent.EnteredDescription(it))
//                },
//                onFocusChange = {
//                    viewModel.onEvent(AddEditOrderEvent.ChangeDescriptionFocus(it))
//                },
//                isHintVisible = orderDescriptionState.isHintVisible,
//                singleLine = true,
//                textStyle = MaterialTheme.typography.h5,
//                modifier = Modifier.fillMaxWidth()
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//            TransparentHintTextField(
//                text = orderMarkState.title,
//                hint = orderMarkState.description,
//                onValueChange = {
//                    viewModel.onEvent(AddEditOrderEvent.EnteredMarking(it))
//                },
//                onFocusChange = {
//                    viewModel.onEvent(AddEditOrderEvent.ChangeMarkingFocus(it))
//                },
//                isHintVisible = orderMarkState.isHintVisible,
//                singleLine = true,
//                textStyle = MaterialTheme.typography.h5,
//                modifier = Modifier.fillMaxWidth()
//            )
            Spacer(modifier = Modifier.height(36.dp))
            TransparentHintTextField(
                text = clientStateName.title,
                hint = clientStateName.description,
                isHintVisible = clientStateName.isHintVisible,
                singleLine = true,
                onValueChange = {
                    viewModel.onEvent(
                        AddEditOrderEvent.EnteredClientName(it)
                    )
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditOrderEvent.ChangeClientNameFocus(it))
                },

                )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = clientStateAddress.title,
                hint = clientStateAddress.description,
                singleLine = true,
                isHintVisible = clientStateAddress.isHintVisible,
                onValueChange = {
                    viewModel.onEvent(
                        AddEditOrderEvent.EnteredClientAddress(it)
                    )
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditOrderEvent.ChangeClientAddressFocus(it))
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = clientStateEmail.title,
                hint = clientStateEmail.description,
                isHintVisible = clientStateEmail.isHintVisible,
                singleLine = true,
                onValueChange = {
                    viewModel.onEvent(
                        AddEditOrderEvent.EnteredClientEmail(it)
                    )
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditOrderEvent.ChangeClientEmailFocus(it))
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = clientStatePhone.title,
                hint = clientStatePhone.description,
                isHintVisible = clientStatePhone.isHintVisible,
                singleLine = true,
                onValueChange = {
                    viewModel.onEvent(
                        AddEditOrderEvent.EnteredClientPhone(it)
                    )
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditOrderEvent.ChangeClientPhoneFocus(it))
                },
            )
        }
    }
}