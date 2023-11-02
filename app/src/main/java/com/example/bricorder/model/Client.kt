package com.example.bricorder.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

data class Client(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val name: String,
    val address: String,
    val phone: String,
    val email: String,
//    @ColumnInfo(name = "show_client_info")
//    var showClientInfo: Boolean = false,
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

