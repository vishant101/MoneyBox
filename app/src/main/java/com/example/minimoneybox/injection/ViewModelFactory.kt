package com.example.minimoneybox.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.minimoneybox.data.database.AppDatabase
import com.example.minimoneybox.data.preferences.AppPreferencesHelper
import com.example.minimoneybox.utils.PREF_FILENAME
import com.example.minimoneybox.utils.PRODUCT_ID
import com.example.minimoneybox.views.login.LoginViewModel
import com.example.minimoneybox.views.accounts.AccountsViewModel
import com.example.minimoneybox.views.individual_account.IndividualAccountViewModel


class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        AppPreferencesHelper.setUp(activity.applicationContext, PREF_FILENAME)
        val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "accounts").build()
        when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                return LoginViewModel() as T
            }
            modelClass.isAssignableFrom(AccountsViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                return AccountsViewModel(db.productDao()) as T
            }
            modelClass.isAssignableFrom(IndividualAccountViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                return IndividualAccountViewModel(db.productDao()) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}