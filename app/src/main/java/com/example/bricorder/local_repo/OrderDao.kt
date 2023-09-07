package com.example.bricorder.local_repo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bricorder.model.Order
import kotlinx.coroutines.flow.Flow


@Dao
interface OrderDao {

    @Query("SELECT * FROM `order`")
    fun getOrders(): Flow<List<Order>>

    @Query("SELECT * FROM `order` WHERE id = :id")
    suspend fun getOrderById(id: Int): Order?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: Order)

    @Delete
    suspend fun delete(order: Order)

}