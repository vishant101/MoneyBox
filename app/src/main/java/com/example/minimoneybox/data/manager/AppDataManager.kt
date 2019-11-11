package com.example.minimoneybox.data.manager

import com.example.minimoneybox.data.model.response.investorproducts.ProductResponse
import com.example.minimoneybox.data.preferences.AppPreferencesHelper


object AppDataManager{
    private val mPreferencesHelper =  AppPreferencesHelper

    fun updateSelectedProductId(productId: Int){
        currentProductId = productId
    }

    fun updateUserInfo(
        accessToken: String?,
        userId: String?,
        name: String?,
        email: String
    ) {
        AppDataManager.accessToken = accessToken
        currentUserId = userId
        currentUserName = name
        currentUserEmail = email
        updateBearerToken(accessToken)
    }

    private var BearerToken: String? = null

    private var accessToken: String?
        get() = mPreferencesHelper.accessToken
        set(accessToken) {
            mPreferencesHelper.accessToken = accessToken
        }

    private var currentUserEmail: String?
        get() = mPreferencesHelper.currentUserEmail
        set(email) {
            mPreferencesHelper.currentUserEmail = email
        }

    private var currentUserId: String?
        get() = mPreferencesHelper.currentUserId
        set(userId) {
            mPreferencesHelper.currentUserId = userId
        }

    var currentUserName: String?
        get() = mPreferencesHelper.currentUserName
        set(userName) {
            mPreferencesHelper.currentUserName = userName
        }

    var currentProductId: Int
        get() = mPreferencesHelper.currentProductId
        set(productId) {
            mPreferencesHelper.currentProductId = productId
        }

    private fun updateBearerToken(accessToken: String?) {
        BearerToken = "Bearer $accessToken"
    }

    fun getBearerToken(): String?{
        return BearerToken
    }
}
