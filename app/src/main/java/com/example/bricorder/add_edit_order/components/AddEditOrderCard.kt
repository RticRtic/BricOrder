package com.example.bricorder.add_edit_order.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AddEditOrderCard(
    modifier: Modifier,
    backgroundColor: Color,
    titleText: String,
    descriptionText: String,
    orderNumberText: String,
    titleHint: String,
    descriptionHint: String,
    orderNumberHint: String,
    onValueChangeTitle: (String) -> Unit,
    onValueChangeDescription: (String) -> Unit,
    onValueChangeOrderNumber: (String) -> Unit,
    onFocusChangeTitle: (FocusState) -> Unit,
    onFocusChangeDescription: (FocusState) -> Unit,
    onFocusChangeOrderNumber: (FocusState) -> Unit,
    isHintVisibleTitle: Boolean,
    isHintVisibleDescription: Boolean,
    isHintVisibleOrderNumber: Boolean,
) {
    Card(
        modifier = modifier,
        border = BorderStroke(1.dp, Color.Black),
        backgroundColor = backgroundColor,
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
        ) {
            TransparentHintTextField(
                text = titleText, textStyle = MaterialTheme.typography.h6,
                hint = titleHint,
                isHintVisible = isHintVisibleTitle,
                onValueChange = onValueChangeTitle,
                onFocusChange = onFocusChangeTitle,
            )
            Spacer(modifier = Modifier.height(10.dp))
            TransparentHintTextField(
                text = descriptionText, textStyle = MaterialTheme.typography.h6,
                hint = descriptionHint,
                isHintVisible = isHintVisibleDescription,
                onValueChange = onValueChangeDescription,
                onFocusChange = onFocusChangeDescription,
            )
            Spacer(modifier = Modifier.height(10.dp))
            TransparentHintTextField(
                text = orderNumberText, textStyle = MaterialTheme.typography.h6,
                hint = orderNumberHint,
                isHintVisible = isHintVisibleOrderNumber,
                onValueChange = onValueChangeOrderNumber,
                onFocusChange = onFocusChangeOrderNumber,
            )
        }
    }
}