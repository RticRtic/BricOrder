package com.example.bricorder.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bricorder.ui.theme.BabyBlue
import com.example.bricorder.ui.theme.LightGreen
import com.example.bricorder.ui.theme.RedOrange
import com.example.bricorder.ui.theme.RedPink
import com.example.bricorder.ui.theme.Violet

@Entity
data class Order(
    @PrimaryKey val id: Int,
    val client: Client,
    val constructor: Constructor,
    val worker: Worker,
    val date: String,
    val status: String,
    val address: String,

) {
    companion object {
        val orderColors = listOf(
            RedOrange,
            LightGreen,
            BabyBlue,
            RedPink,
            LightGreen,
            Violet
        )
    }
}

class InvalidOrderException(message: String): Exception(message)