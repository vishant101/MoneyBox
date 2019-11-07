package com.example.minimoneybox.data.model.response.investorproducts

import androidx.room.Entity

@Entity
data class InvestorAccount (
    val ContributionsNet: Float,
    val EarningsNet: Float,
    val EarningsAsPercentage: Float
)