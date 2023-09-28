package com.example.bricorder.add_edit_order.components

import android.annotation.SuppressLint
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bricorder.model.Client
import com.example.bricorder.model.Order
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
    val clientState = viewModel.client.value

    val scaffoldState = rememberScaffoldState()

    val noteBackgroundAnimatable = remember {
        Animatable(
            Color(if (orderColor != -1) orderColor else viewModel.orderColor.value)
        )
    }
    val scope = rememberCoroutineScope()

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
                .background(noteBackgroundAnimatable.value)
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
                                scope.launch {
                                    noteBackgroundAnimatable.animateTo(
                                        targetValue = Color(colorInt),
                                        animationSpec = tween(
                                            durationMillis = 500
                                        )
                                    )
                                }
                                viewModel.onEvent(AddEditOrderEvent.ChangeColor(colorInt))
                            }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = titleState.title,
                hint = titleState.description,
                onValueChange = {
                    viewModel.onEvent(AddEditOrderEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditOrderEvent.ChangeTitleFocus(it))
                },
                isHintVisible = titleState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = orderDescriptionState.title,
                hint = orderDescriptionState.description,
                onValueChange = {
                    viewModel.onEvent(AddEditOrderEvent.EnteredDescription(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditOrderEvent.ChangeDescriptionFocus(it))
                },
                isHintVisible = orderDescriptionState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = orderMarkState.title,
                hint = orderMarkState.description,
                onValueChange = {
                    viewModel.onEvent(AddEditOrderEvent.EnteredMarking(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditOrderEvent.ChangeMarkingFocus(it))
                },
                isHintVisible = orderMarkState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.h5,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(36.dp))
            TransparentHintTextField(
                text = clientState.name,
                hint = clientState.description,
                isHintVisible = clientState.isHintVisible,
                onValueChange = { clientName ->
                    viewModel.onEvent(
                        AddEditOrderEvent.EnteredClient(
                            clientName,
                            clientState.address,
                            clientState.phone,
                            clientState.email,
                            clientState.id ?: 0
                        )
                    )
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditOrderEvent.ChangeClientFocus(it))
                },
            )

            Spacer(modifier = Modifier.height(2.dp))
            TransparentHintTextField(
                text = clientState.address,
                hint = clientState.description,
                isHintVisible = clientState.isHintVisible,
                onValueChange = { clientAddress ->
                    viewModel.onEvent(
                        AddEditOrderEvent.EnteredClient(
                            clientState.name,
                            clientAddress,
                            clientState.phone,
                            clientState.email,
                            clientState.id ?: 0
                        )
                    )
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditOrderEvent.ChangeClientFocus(it))
                },
            )
        }
    }
}