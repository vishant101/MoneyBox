package com.example.minimoneybox.data.manager

import com.example.minimoneybox.data.preferences.AppPreferencesHelper


object AppDataManager{
    private val mPreferencesHelper =  AppPreferencesHelper

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

    private var currentUserName: String?
        get() = mPreferencesHelper.currentUserName
        set(userName) {
            mPreferencesHelper.currentUserName = userName
        }

    private fun updateBearerToken(accessToken: String?) {
        BearerToken = "Bearer $accessToken"
    }

    fun getBearerToken(): String?{
        return BearerToken
    }
}
