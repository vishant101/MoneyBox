package com.example.minimoneybox.data.model.response.investorproducts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuickAddDeposit (
    @PrimaryKey
    val Amount: Float
)