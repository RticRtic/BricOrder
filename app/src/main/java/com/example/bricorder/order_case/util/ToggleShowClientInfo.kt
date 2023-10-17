package com.example.bricorder.order_case.util

import com.example.bricorder.local_repo.LocalRepository

class ToggleShowClientInfo(private val repository: LocalRepository) {
    suspend operator fun invoke(id: Int, showClientInfo: Boolean) {
        return repository.updateShowClientInfo(id, showClientInfo)
    }
}