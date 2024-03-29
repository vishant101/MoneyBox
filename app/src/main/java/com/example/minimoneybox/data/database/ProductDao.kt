package com.example.minimoneybox.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.minimoneybox.data.model.response.investorproducts.InvestorProducts
import com.example.minimoneybox.data.model.response.investorproducts.ProductResponse

@Dao
interface ProductDao {
    @get:Query("SELECT * FROM ProductResponse")
    val all: List<ProductResponse>

    @Query("SELECT * FROM ProductResponse WHERE Id=:Id ")
    fun getProduct(Id: Int): ProductResponse

    @Query("SELECT * FROM InvestorProducts")
    fun getInvestorProducts(): InvestorProducts

    @Query("UPDATE ProductResponse SET Moneybox=:value WHERE Id=:Id")
    fun updateMoneyBox(Id: Int, value: Float?)

    @Insert
    fun insertAll(vararg productResponses: ProductResponse)

    @Insert
    fun insert(productResponse: ProductResponse)

    @Insert
    fun insertFullResponse(investorProducts: InvestorProducts)
}