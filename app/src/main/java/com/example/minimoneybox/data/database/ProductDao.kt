package com.example.minimoneybox.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.minimoneybox.data.model.response.investorproducts.ProductResponse

@Dao
interface ProductDao {
    @get:Query("SELECT * FROM ProductResponse")
    val all: List<ProductResponse>

    @Insert
    fun insertAll(vararg productResponses: ProductResponse)

    @Insert
    fun insert(productResponse: ProductResponse)
}