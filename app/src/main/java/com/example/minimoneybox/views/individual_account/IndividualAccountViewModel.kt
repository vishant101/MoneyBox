package com.example.minimoneybox.views.individual_account

import com.example.minimoneybox.data.database.ProductDao
import com.example.minimoneybox.network.AccountApi
import com.example.minimoneybox.views.base.BaseViewModel
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class IndividualAccountViewModel(private val productDao: ProductDao): BaseViewModel() {
    @Inject
    lateinit var accountApi: AccountApi
    private lateinit var subscription: Disposable

    init {

    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}