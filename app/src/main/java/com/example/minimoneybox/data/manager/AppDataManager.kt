package com.example.minimoneybox.data.manager

import com.example.minimoneybox.data.preferences.AppPreferencesHelper


object AppDataManager{
    private val mPreferencesHelper =  AppPreferencesHelper
    // val mApiHelper = ApiHelper

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
        // updateApiHeader(accessToken)
    }

    private var accessToken: String?
        get() = mPreferencesHelper.accessToken
        set(accessToken) {
            mPreferencesHelper.accessToken = accessToken
            // mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken)
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

//    fun updateApiHeader(accessToken: String?) {
//        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken)
//    }
}
