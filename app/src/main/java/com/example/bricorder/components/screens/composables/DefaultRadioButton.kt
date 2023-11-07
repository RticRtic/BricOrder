package com.example.bricorder.components.screens.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.bricorder.R

@Composable
fun DefaultRadioButton(
    text: String,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    val denim = colorResource(id = R.color.denim)
    Column(
        modifier = modifier
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        RadioButton(
            selected = selected,
            onClick = onSelect,
            colors = RadioButtonDefaults.colors(
                selectedColor = denim,
                unselectedColor = MaterialTheme.colors.onBackground
            )
        )
        Text(text = text, style = MaterialTheme.typography.subtitle2)
    }
}
