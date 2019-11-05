package com.example.minimoneybox.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.example.minimoneybox.injection.DaggerViewModelInjector
import com.example.minimoneybox.injection.ViewModelInjector
import com.example.minimoneybox.network.NetworkModule

abstract class BaseViewModel:ViewModel(){
    private val mIsLoading = ObservableBoolean()

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    fun setIsLoading(isLoading: Boolean) {
        mIsLoading.set(isLoading)
    }

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is LoginViewModel -> injector.inject(this)
        }
    }
}