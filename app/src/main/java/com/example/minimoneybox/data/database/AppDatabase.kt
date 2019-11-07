package com.example.minimoneybox.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.minimoneybox.data.model.response.investorproducts.ProductResponse
import com.example.minimoneybox.utils.Converters

@Database(entities = [ProductResponse::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}