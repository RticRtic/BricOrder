package com.example.bricorder.add_edit_order.components

import androidx.compose.ui.focus.FocusState

sealed class AddEditOrderEvent {
    data class EnteredTitle(val value: String): AddEditOrderEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditOrderEvent()
    data class EnteredDescription(val value: String): AddEditOrderEvent()
    data class ChangeDescriptionFocus(val focusState: FocusState): AddEditOrderEvent()
    data class ChangeColor(val color: Int): AddEditOrderEvent()

    object SaveOrder: AddEditOrderEvent()
}
