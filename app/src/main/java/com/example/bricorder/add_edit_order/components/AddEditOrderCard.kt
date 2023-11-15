package com.example.bricorder.add_edit_order.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp

@Composable
fun AddEditOrderCard(
    backgroundColor: Color,
    text: String,
    hint: String,
    onValueChange: (String) -> Unit,
    onFocusChange: (FocusState) -> Unit,
    isHintVisible: Boolean,
    ) {
    Card(
        modifier = Modifier.fillMaxSize(),
        border = BorderStroke(1.dp, Color.Black),
        backgroundColor = backgroundColor,
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            TransparentHintTextField(
                text = text,
                hint = hint,
                isHintVisible = isHintVisible,
                onValueChange = onValueChange,
                onFocusChange = onFocusChange,
            )
        }
    }
}