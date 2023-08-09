package com.example.bricorder.model
import androidx.room.TypeConverter
import com.google.gson.Gson

data class Worker(
    val name: String,
    val address: String,
    val phone: String,
    val email: String,
    val id: Int? = null
)

class WorkerTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromWorker(worker: Worker): String {
        return gson.toJson(worker)
    }

    @TypeConverter
    fun toWorker(value: String): Worker {
        return gson.fromJson(value, Worker::class.java)
    }
}
