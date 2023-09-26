package com.example.bricorder.add_edit_order.components

import androidx.compose.ui.focus.FocusState
import com.example.bricorder.model.Client

sealed class AddEditOrderEvent {
    data class EnteredTitle(val value: String) : AddEditOrderEvent()
    data class EnteredDescription(val value: String) : AddEditOrderEvent()
    data class EnteredMarking(val value: String) : AddEditOrderEvent()
    data class ChangeColor(val color: Int) : AddEditOrderEvent()
    data class EnteredClient(val value: String) : AddEditOrderEvent()

    data class ChangeTitleFocus(val focusState: FocusState) : AddEditOrderEvent()
    data class ChangeDescriptionFocus(val focusState: FocusState) : AddEditOrderEvent()
    data class ChangeMarkingFocus(val focusState: FocusState) : AddEditOrderEvent()
    data class ChangeClientFocus(val focusState: FocusState) : AddEditOrderEvent()

    object SaveOrder : AddEditOrderEvent()
}
