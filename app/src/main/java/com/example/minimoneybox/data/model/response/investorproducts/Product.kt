package com.example.minimoneybox.data.model.response.investorproducts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product (
    @PrimaryKey
    val Id: Int,
    val Name: String,
    val CategoryType: String,
    val Type: String,
    val FriendlyName: String,
    val CanWithdraw: Boolean,
    val ProductHexCode: String,
    val AnnualLimit: Float,
    val DepositLimit: Float
)