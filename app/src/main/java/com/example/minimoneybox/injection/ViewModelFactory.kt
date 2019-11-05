package com.example.minimoneybox.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.minimoneybox.views.login.LoginViewModel
import com.example.minimoneybox.views.useraccounts.AccountsViewModel


class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                return LoginViewModel() as T
            }
            modelClass.isAssignableFrom(AccountsViewModel::class.java) -> {
                @Suppress("UNCHECKED_CAST")
                return AccountsViewModel() as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}