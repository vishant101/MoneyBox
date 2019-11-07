package com.example.minimoneybox.data.model.response.investorproducts

data class Product (
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