package com.example.bricorder.di

import android.app.Application
import androidx.room.Room
import com.example.bricorder.local_repo.LocalRepositoryImpl
import com.example.bricorder.local_repo.LocalRepository
import com.example.bricorder.local_repo.OrderDataBase
import com.example.bricorder.order_case.AddOrder
import com.example.bricorder.order_case.DeleteOrder
import com.example.bricorder.order_case.GetOrder
import com.example.bricorder.order_case.GetOrders
import com.example.bricorder.order_case.OrderUseCases
import com.example.bricorder.order_case.ToggleOngoingColor
import com.example.bricorder.order_case.util.ToggleShowClientInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

object AppModule {
    @Provides
    @Singleton
    fun provideOrderDatabase(app: Application): OrderDataBase {
        return Room.databaseBuilder(
            app,
            OrderDataBase::class.java,
            OrderDataBase.DATABASE_NAME
        ).build(
        )
    }

    @Provides
    @Singleton
    fun provideOrderRepository(db: OrderDataBase): LocalRepository {
        return LocalRepositoryImpl(db.orderDao)
    }

    @Provides
    @Singleton
    fun provideOrderUseCases(repository: LocalRepository): OrderUseCases {
        return OrderUseCases(
            getOrders = GetOrders(repository),
            deleteOrder = DeleteOrder(repository),
            addOrder = AddOrder(repository),
            getOrder = GetOrder(repository),
            toggleOnGoingColor = ToggleOngoingColor(repository),
            toggleShowClientInfo = ToggleShowClientInfo(repository)
        )
    }
}