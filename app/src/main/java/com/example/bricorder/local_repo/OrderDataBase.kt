package com.example.bricorder.local_repo

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bricorder.model.ClientTypeConverter
import com.example.bricorder.model.ConstructorTypeConverter
import com.example.bricorder.model.Order
import com.example.bricorder.model.WorkerTypeConverter


@Database(entities = [Order::class], version = 1)
@TypeConverters(ClientTypeConverter::class, WorkerTypeConverter::class, ConstructorTypeConverter::class)


abstract class OrderDataBase: RoomDatabase() {
    abstract val orderDao: OrderDao

    companion object {
        const val DATABASE_NAME = "order_db"
    }
}