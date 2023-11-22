package com.example.bricorder.orders.composables

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bricorder.R
import com.example.bricorder.model.Order
import com.example.bricorder.orders.OrdersEvent
import com.example.bricorder.orders.OrdersViewModel

val TAG = "!!!"

@Composable
fun ClientInfo(
    viewModel: OrdersViewModel = hiltViewModel(),
    order: Order,
) {

    val showClientInfo = remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        if (showClientInfo.value) 0.9f else 1f,
        label = ""
    )


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .background(
                if (showClientInfo.value) Color.White.copy(alpha = 0.5f) else Color.White.copy(
                    alpha = 0.3f
                )
            )
            .clickable { showClientInfo.value = !showClientInfo.value }
            .scale(scale)
    ) {
        if (showClientInfo.value)
            Column(modifier = Modifier.padding(8.dp)) {
                Spacer(modifier = Modifier.height(2.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close",
                    )
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "Client-Name")
                    Spacer(modifier = Modifier.padding(start = 10.dp))
                    Text(
                        text = order.client?.name ?: "No Client Name",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(imageVector = Icons.Default.Home, contentDescription = "Client-Address")
                    Spacer(modifier = Modifier.padding(start = 10.dp))
                    Text(
                        text = order.client?.address ?: "No Client Address",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "Client-Email")
                    Spacer(modifier = Modifier.padding(start = 10.dp))
                    Text(
                        text = order.client?.email ?: "No Client Email",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(imageVector = Icons.Default.Phone, contentDescription = "Client-Phone")
                    Spacer(modifier = Modifier.padding(start = 10.dp))
                    Text(
                        text = order.client?.phone ?: "No Client Phone",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        else
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Row() {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Client-Person",
                        modifier = Modifier.align(Alignment.CenterVertically),
                    )
                    Text(
                        text = order.client?.name + " " + "...",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "More Info",
                    tint = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(8.dp)
                )
            }
    }
}