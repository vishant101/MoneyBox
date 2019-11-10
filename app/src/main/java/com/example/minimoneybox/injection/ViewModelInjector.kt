package com.example.minimoneybox.injection

import com.example.minimoneybox.network.NetworkModule
import com.example.minimoneybox.views.login.LoginViewModel
import com.example.minimoneybox.views.accounts.AccountsViewModel
import com.example.minimoneybox.views.accounts.account_element.AccountElementViewModel
import com.example.minimoneybox.views.individual_account.IndividualAccountViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(loginViewModel: LoginViewModel)

    fun inject(accountsViewModel: AccountsViewModel)

    fun inject(accountsElementViewModel: AccountElementViewModel)

    fun inject(individualAccountViewModel: IndividualAccountViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}