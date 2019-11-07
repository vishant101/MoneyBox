package com.example.minimoneybox.views.accounts

import android.util.Log
import com.example.minimoneybox.data.manager.AppDataManager
import com.example.minimoneybox.network.AccountApi
import com.example.minimoneybox.utils.RESULT
import com.example.minimoneybox.views.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AccountsViewModel: BaseViewModel() {

    @Inject
    lateinit var accountApi: AccountApi

    private lateinit var subscription: Disposable

    init {
        loadAccounts()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadAccounts(){
        setIsLoading(true)

        subscription = Observable.fromCallable{}
            .concatMap {
                accountApi.getAccounts( AppDataManager.getBearerToken() )
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { setIsLoading(true) }
            .doOnTerminate { setIsLoading(false) }
            .subscribe(
                { result -> Log.i(RESULT, result.toString()) },
                { error -> Log.e(RESULT, error.toString()) }
            )
    }


}