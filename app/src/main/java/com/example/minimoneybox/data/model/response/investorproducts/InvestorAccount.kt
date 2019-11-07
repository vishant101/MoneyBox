package com.example.minimoneybox.data.model.response.investorproducts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InvestorAccount (
    @PrimaryKey
    val ContributionsNet: Float,
    val EarningsNet: Float,
    val EarningsAsPercentage: Float
)