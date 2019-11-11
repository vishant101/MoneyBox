package com.example.minimoneybox.data.model.response.investorproducts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InvestorProducts (
    @PrimaryKey
    val MoneyboxEndOfTaxYear: String,
    val TotalPlanValue: Float,
    val TotalEarnings: Float,
    val TotalContributionsNet: Float,
    val TotalEarningsAsPercentage: Float,
    val ProductResponses: List<ProductResponse>
)