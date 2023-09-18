package com.example.bricorder.order_case

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.bricorder.local_repo.LocalRepository
import com.example.bricorder.model.Order

class ToggleOngoingColor(private val repository: LocalRepository) {

    suspend operator fun invoke(id: Int, onGoing: Boolean) {
        return repository.updateOngoingColor(id, onGoing)
    }

}