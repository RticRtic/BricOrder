
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.bricorder.model.Order
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import com.example.bricorder.orders.composables.ClientInfo
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun OrderItem(
    order: Order,
    modifier: Modifier,
    cornerRadius: Dp = 10.dp,
    cutCornerSize: Dp = 30.dp,
    onDelete: () -> Unit,
    onGoing: () -> Unit
) {
    val expanded = remember { mutableStateOf(false) }

    Box(
        modifier = modifier

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
                        androidx.core.graphics.ColorUtils.blendARGB(
                            if (order.onGoing) Color.Transparent.toArgb() else Color.Red.toArgb(),
                            Color.Transparent.toArgb(),
                            0.3f
                        )
                    ),
                    topLeft = Offset(size.width - cutCornerSize.toPx(), -100f),
                    size = Size(cutCornerSize.toPx() + 100f, cutCornerSize.toPx() + 100f),
                    cornerRadius = CornerRadius(cornerRadius.toPx())

                )
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(end = 30.dp),
            horizontalArrangement = Arrangement.End) {
            Text(
                text = currentTimeMillisToReadableFormat(order.timestamp),
                style = MaterialTheme.typography.subtitle2,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = order.title,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Row {
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .shadow(15.dp, CircleShape)
                            .clip(CircleShape)
                            .background(if (order.onGoing) Color.Green else Color.Red)
                            .border(
                                width = 3.dp,
                                color = Color.Transparent,
                                shape = CircleShape
                            )
                            .clickable { onGoing() }

                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        if (order.onGoing) "On Going" else "Cancelled",
                        style = TextStyle(fontFamily = FontFamily.Serif)
                    )
                }

            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = order.description,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(10.dp))
            ClientInfo(order = order)
            Spacer(modifier = Modifier.height(10.dp))
        }
        Text(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 8.dp, bottom = 2.dp),
            text = "Mark: " + order.orderMark,
            style = MaterialTheme.typography.subtitle2
        )
//        Text(
//            modifier = Modifier
//                .align(Alignment.TopEnd)
//                .padding(end = 30.dp, bottom = 2.dp),
//            text = currentTimeMillisToReadableFormat(order.timestamp),
//            style = MaterialTheme.typography.subtitle2,
//        )
        if (!order.onGoing) IconButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = onDelete,
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete order",
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}

private fun currentTimeMillisToReadableFormat(currentTimeMillis: Long): String {
    val format = SimpleDateFormat("dd/MM/yyyy ", Locale.getDefault())
    return format.format(currentTimeMillis)
}