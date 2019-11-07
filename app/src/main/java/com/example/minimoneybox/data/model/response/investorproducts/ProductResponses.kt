package com.example.minimoneybox.data.model.response.investorproducts

data class ProductResponses (
    val Id: Int,
    val PlanValue: Float,
    val Moneybox: Float,
    val SubscriptionAmount: Float,
    val TotalFees: Float,
    val IsSelected: Boolean,
    val IsFavourite: Boolean,
    val ContributionStatus: String,
    val Product: Product,
    val InvestorAccount: InvestorAccount,
    val Personalisation: Personalisation,
    val CollectionDayMessage: String
)