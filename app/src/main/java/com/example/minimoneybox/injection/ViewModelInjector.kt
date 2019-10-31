package com.example.minimoneybox.injection

import com.example.minimoneybox.network.NetworkModule
import com.example.minimoneybox.viewmodel.LoginViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(loginViewModel: LoginViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}