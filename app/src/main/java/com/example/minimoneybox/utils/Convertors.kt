package com.example.minimoneybox.utils

import androidx.room.TypeConverter
import com.example.minimoneybox.data.model.response.investorproducts.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    private val gson = Gson()
    private val investorProductType = object: TypeToken<InvestorProducts>() {}.type
    private val productResponseType = object: TypeToken<List<ProductResponse>>() {}.type
    private val productType = object: TypeToken<Product>() {}.type
    private val investorAccountType = object: TypeToken<InvestorAccount>() {}.type
    private val personalisationType = object: TypeToken<Personalisation>() {}.type
    private val quickAddDepositType = object: TypeToken<QuickAddDeposit>() {}.type
    private val hideAccountsType = object: TypeToken<HideAccounts>() {}.type

    @TypeConverter
    fun stringToInvestorProducts(json: String): InvestorProducts {
        return gson.fromJson(json, investorProductType)
    }

    @TypeConverter
    fun investorProductsToString(nestedData: InvestorProducts): String {
        return gson.toJson(nestedData, investorProductType)
    }

    @TypeConverter
    fun stringToProductResponse(json: String): List<ProductResponse> {
        return gson.fromJson(json, productResponseType)
    }

    @TypeConverter
    fun productResponseToString(nestedData: List<ProductResponse>): String {
        return gson.toJson(nestedData, productResponseType)
    }

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
    fun personalisationToString(nestedData: Personalisation): String {
        return gson.toJson(nestedData, personalisationType)
    }

    @TypeConverter
    fun stringToQuickAddDeposit(json: String): QuickAddDeposit {
        return gson.fromJson(json, quickAddDepositType)
    }

    @TypeConverter
    fun quickAddDepositToString(nestedData: QuickAddDeposit): String {
        return gson.toJson(nestedData, quickAddDepositType)
    }

    @TypeConverter
    fun stringToHideAccounts(json: String): HideAccounts {
        return gson.fromJson(json, hideAccountsType)
    }

    @TypeConverter
    fun hideAccountsToString(nestedData: HideAccounts): String {
        return gson.toJson(nestedData, hideAccountsType)
    }
}