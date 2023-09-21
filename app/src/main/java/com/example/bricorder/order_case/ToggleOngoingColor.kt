package com.example.bricorder.order_case
import com.example.bricorder.local_repo.LocalRepository

class ToggleOngoingColor(private val repository: LocalRepository) {

    suspend operator fun invoke(id: Int, onGoing: Boolean) {
        return repository.updateOngoingColor(id, onGoing)
    }

}