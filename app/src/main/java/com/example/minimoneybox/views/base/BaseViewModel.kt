package com.example.minimoneybox.views.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.example.minimoneybox.injection.DaggerViewModelInjector
import com.example.minimoneybox.injection.ViewModelInjector
import com.example.minimoneybox.network.NetworkModule
import com.example.minimoneybox.views.login.LoginViewModel
import com.example.minimoneybox.views.accounts.AccountsViewModel

abstract class BaseViewModel:ViewModel(){
    private val mIsLoading = ObservableBoolean()

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    fun setIsLoading(isLoading: Boolean) {
        mIsLoading.set(isLoading)
    }

    fun getIsLoading(): ObservableBoolean {
        return mIsLoading
    }

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is LoginViewModel-> injector.inject(this)
            is AccountsViewModel -> injector.inject(this)
        }
    }
}