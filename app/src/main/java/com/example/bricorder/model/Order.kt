package com.example.bricorder.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bricorder.ui.BabyBlue
import com.example.bricorder.ui.LightGreen
import com.example.bricorder.ui.RedOrange
import com.example.bricorder.ui.RedPink
import com.example.bricorder.ui.Violet

@Entity
data class Order(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val timestamp: Long,
    val color: Int,
//    val client: Client,
//    val constructor: Constructor,
//    val worker: Worker,
//    val date: String,
//    val status: String,
//    val address: String,

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

class InvalidOrderException(message: String) : Exception(message)