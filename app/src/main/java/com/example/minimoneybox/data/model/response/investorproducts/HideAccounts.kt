package com.example.minimoneybox.data.model.response.investorproducts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HideAccounts (
    @PrimaryKey
    val Enabled: Boolean,
    val IsHidden: Boolean,
    val Sequence: Int
)