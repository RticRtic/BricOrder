package com.example.bricorder.model

import androidx.room.Entity
import androidx.room.TypeConverter
import com.google.gson.Gson

data class Constructor(
    val name: String,
    val address: String,
    val phone: String,
    val email: String,
    val id: Int? = null
)

class ConstructorTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromConstructor(constructor: Constructor): String {
        return gson.toJson(constructor)
    }

    @TypeConverter
    fun toConstructor(value: String): Constructor {
        return gson.fromJson(value, Constructor::class.java)
    }
}
