package com.example.minimoneybox.data.model.response.investorproducts

import androidx.room.Entity

@Entity
data class InvestorProducts (
    val MoneyboxEndOfTaxYear: String,
    val TotalPlanValue: Float,
    val TotalEarnings: Float,
    val TotalContributionsNet: Float,
    val TotalEarningsAsPercentage: Float,
    val ProductResponses: List<ProductResponse>
)