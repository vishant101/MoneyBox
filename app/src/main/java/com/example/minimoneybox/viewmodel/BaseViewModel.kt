package com.example.minimoneybox.viewmodel

import androidx.lifecycle.ViewModel
import com.example.minimoneybox.injection.DaggerViewModelInjector
import com.example.minimoneybox.injection.ViewModelInjector
import com.example.minimoneybox.network.NetworkModule

abstract class BaseViewModel:ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is LoginViewModel -> injector.inject(this)
        }
    }
}