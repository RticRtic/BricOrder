package com.example.bricorder.add_edit_order.components

import androidx.compose.ui.focus.FocusState

sealed class AddEditOrderEvent {
    data class EnteredTitle(val value: String) : AddEditOrderEvent()
    data class EnteredDescription(val value: String) : AddEditOrderEvent()
    data class EnteredMarking(val value: String) : AddEditOrderEvent()
    data class ChangeColor(val color: Int) : AddEditOrderEvent()
    data class EnteredClientName(val value: String) : AddEditOrderEvent()
    data class EnteredClientAddress(val value: String) : AddEditOrderEvent()
    data class EnteredClientPhone(val value: String) : AddEditOrderEvent()
    data class EnteredClientEmail(val value: String) : AddEditOrderEvent()

    data class ChangeTitleFocus(val focusState: FocusState) : AddEditOrderEvent()
    data class ChangeDescriptionFocus(val focusState: FocusState) : AddEditOrderEvent()
    data class ChangeMarkingFocus(val focusState: FocusState) : AddEditOrderEvent()
    data class ChangeClientNameFocus(val focusState: FocusState) : AddEditOrderEvent()
    data class ChangeClientAddressFocus(val focusState: FocusState) : AddEditOrderEvent()
    data class ChangeClientPhoneFocus(val focusState: FocusState) : AddEditOrderEvent()
    data class ChangeClientEmailFocus(val focusState: FocusState) : AddEditOrderEvent()

    object SaveOrder : AddEditOrderEvent()
}