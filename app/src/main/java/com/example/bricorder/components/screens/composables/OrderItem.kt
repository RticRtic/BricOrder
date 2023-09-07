package com.example.bricorder.components.screens.composables

import android.widget.Space
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.bricorder.model.Order
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun OrderItem(
    order: Order,
    modifier: Modifier,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp,
    onDelete: () -> Unit

    ) {
    Box(
        modifier = modifier,
    ) {
        Canvas(
            modifier = Modifier.matchParentSize()
        ) {
            val clipPath = Path().apply {
                lineTo(size.width - cutCornerSize.toPx(), 0f)
                lineTo(size.width, cutCornerSize.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }
            clipPath(clipPath) {
                drawRoundRect(
                    color = Color(order.color),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
                drawRoundRect(
                    color = Color(
                        androidx.core.graphics.ColorUtils.blendARGB(order.color, 0x000000, 0.2f)
                    ),
                    topLeft = Offset(size.width - cutCornerSize.toPx(), -100f),
                    size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f),
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)
        ) {
            Text(
                text = order.title,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = order.description,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )
        }
        IconButton(
            onClick = onDelete,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete order",
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}