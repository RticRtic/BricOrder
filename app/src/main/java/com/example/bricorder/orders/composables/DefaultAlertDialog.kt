package com.example.bricorder.orders.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun DefaultAlertDialog(
    title: String,
    message: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    icon: ImageVector,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        AlertDialog(
            icon = { Icon(icon, contentDescription = null) },
            title = { Text(text = title) },
            text = { Text(text = message) },
            onDismissRequest = { onDismiss() },
            confirmButton = {
                TextButton(onClick = {onConfirm()}) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = {onDismiss()}) {
                    Text(text = "Dismiss")
                }
            }
        )
    }
}