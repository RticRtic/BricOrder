package com.example.bricorder.add_edit_order.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddEditOrderClientCard(
    modifier: Modifier,
    backgroundColor: Color,
    clientText: String,
    clientAddressText: String,
    clientEmailText: String,
    clientPhoneText: String,
    clientHint: String,
    clientAddressHint: String,
    clientEmailHint: String,
    clientPhoneHint: String,
    onValueChangeClient: (String) -> Unit,
    onValueChangeClientAddress: (String) -> Unit,
    onValueChangeClientEmail: (String) -> Unit,
    onValueChangeClientPhone: (String) -> Unit,
    onFocusChangeClient: (FocusState) -> Unit,
    onFocusChangeClientAddress: (FocusState) -> Unit,
    onFocusChangeClientEmail: (FocusState) -> Unit,
    onFocusChangeClientPhone: (FocusState) -> Unit,
    isHintVisibleClient: Boolean,
    isHintVisibleClientAddress: Boolean,
    isHintVisibleClientEmail: Boolean,
    isHintVisibleClientPhone: Boolean,
) {

    Card(
        modifier = modifier,
        backgroundColor = backgroundColor,
    ) {
        Column(modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.padding(top = 5.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.padding(start = 2.dp))
                Icon(imageVector = Icons.Default.Person, contentDescription = "Client")
                Spacer(modifier = Modifier.padding(start = 10.dp))
                TransparentHintTextField(
                    text = clientText,
                    hint = clientHint,
                    isHintVisible = isHintVisibleClient,
                    onValueChange = onValueChangeClient,
                    onFocusChange = onFocusChangeClient,
                    singleLine = true,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.padding(start = 2.dp))
                Icon(imageVector = Icons.Default.Home, contentDescription = "Address")
                Spacer(modifier = Modifier.padding(start = 10.dp))
                TransparentHintTextField(
                    text = clientAddressText,
                    hint = clientAddressHint,
                    isHintVisible = isHintVisibleClientAddress,
                    onValueChange = onValueChangeClientAddress,
                    onFocusChange = onFocusChangeClientAddress,
                    singleLine = true,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.padding(start = 2.dp))
                Icon(imageVector = Icons.Default.Email, contentDescription = "Email")
                Spacer(modifier = Modifier.padding(start = 10.dp))
                TransparentHintTextField(
                    text = clientEmailText,
                    hint = clientEmailHint,
                    isHintVisible = isHintVisibleClientEmail,
                    onValueChange = onValueChangeClientEmail,
                    onFocusChange = onFocusChangeClientEmail,
                    singleLine = true,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.padding(start = 2.dp))
                Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone")
                Spacer(modifier = Modifier.padding(start = 10.dp))
                TransparentHintTextField(
                    text = clientPhoneText,
                    hint = clientPhoneHint,
                    isHintVisible = isHintVisibleClientPhone,
                    onValueChange = onValueChangeClientPhone,
                    onFocusChange = onFocusChangeClientPhone,
                    singleLine = true,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            Spacer(modifier = Modifier.padding(top = 5.dp))
        }
    }

}