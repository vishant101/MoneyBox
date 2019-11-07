package com.example.minimoneybox.utils

import androidx.room.TypeConverter
import com.example.minimoneybox.data.model.response.investorproducts.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    private val gson = Gson()
    private val productType = object: TypeToken<Product>() {}.type
    private val investorAccountType = object: TypeToken<InvestorAccount>() {}.type
    private val personalisationType = object: TypeToken<Personalisation>() {}.type

    @TypeConverter
    fun stringToProduct(json: String): Product {
        return gson.fromJson(json, productType)
    }

    @TypeConverter
    fun productToString(nestedData: Product): String {
        return gson.toJson(nestedData, productType)
    }

    @TypeConverter
    fun stringToInvestorAccount(json: String): InvestorAccount {
        return gson.fromJson(json, investorAccountType)
    }

    @TypeConverter
    fun investorAccountToString(nestedData: InvestorAccount): String {
        return gson.toJson(nestedData, investorAccountType)
    }

    @TypeConverter
    fun stringToPersonalisation(json: String): Personalisation {
        return gson.fromJson(json, personalisationType)
    }

    @TypeConverter
    fun productToString(nestedData: Personalisation): String {
        return gson.toJson(nestedData, personalisationType)
    }
}