package com.example.minimoneybox.data.preferences

import android.content.Context
import android.content.SharedPreferences

object AppPreferencesHelper {

    fun setUp(context: Context, prefFileName: String){
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)
    }

    private const val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"
    private const val PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL"
    private const val PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID"
    private const val PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME"
    private const val PREF_KEY_CURRENT_PRODUCT_ID = "PREF_KEY_CURRENT_PRODUCT_ID"

    private lateinit var mPrefs: SharedPreferences

    var accessToken: String?
        get() = mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null)
        set(accessToken) = mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply()

    var currentUserEmail: String?
        get() = mPrefs.getString(PREF_KEY_CURRENT_USER_EMAIL, null)
        set(email) = mPrefs.edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply()

    var currentUserId: String?
        get() = mPrefs.getString(PREF_KEY_CURRENT_USER_ID, null)
        set(userId) = mPrefs.edit().putString(PREF_KEY_CURRENT_USER_ID, userId).apply()

    var currentUserName: String?
        get() = mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, null)
        set(userName) = mPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply()

    var currentProductId: Int
        get() = mPrefs.getInt(PREF_KEY_CURRENT_PRODUCT_ID, 0)
        set(productId) = mPrefs.edit().putInt(PREF_KEY_CURRENT_PRODUCT_ID, productId).apply()
}
