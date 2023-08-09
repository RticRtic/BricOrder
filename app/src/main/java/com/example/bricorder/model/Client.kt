package com.example.bricorder.model

import androidx.room.TypeConverter
import com.google.gson.Gson

data class Client(
    val name: String,
    val address: String,
    val phone: String,
    val email: String,
    val id: Int? = null

)

class ClientTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromClient(client: Client): String {
        return gson.toJson(client)
    }

    @TypeConverter
    fun toClient(value: String): Client {
        return gson.fromJson(value, Client::class.java)
    }
}

