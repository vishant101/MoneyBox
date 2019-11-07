package com.example.minimoneybox.views.accounts

import android.util.Log
import com.example.minimoneybox.data.database.ProductDao
import com.example.minimoneybox.data.manager.AppDataManager
import com.example.minimoneybox.data.model.response.investorproducts.ProductResponse
import com.example.minimoneybox.network.AccountApi
import com.example.minimoneybox.utils.RESULT
import com.example.minimoneybox.views.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AccountsViewModel(private val productDao: ProductDao): BaseViewModel() {
    @Inject
    lateinit var accountApi: AccountApi
    val accountsListAdapter: AccountsListAdapter = AccountsListAdapter()
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

        subscription = Observable.fromCallable{ productDao.all }
            .concatMap {
                accountApi.getAccounts( AppDataManager.getBearerToken() )
//                    dbAccountList ->
//                if(dbAccountList.isEmpty())
//                    accountApi.getAccounts( AppDataManager.getBearerToken() ).concatMap {
//                            apiAccountList -> productDao.insertAll(*apiAccountList.ProductResponses.toTypedArray())
//                        Observable.just(apiAccountList.ProductResponses)
//                    }
//                else
//                    Observable.just(dbAccountList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { setIsLoading(true) }
            .doOnTerminate { setIsLoading(false) }
            .subscribe(
                { result -> onRetrieveAccountsSuccess(result.ProductResponses) },
                { error -> Log.e(RESULT, error.toString()) }
            )
    }

    private fun onRetrieveAccountsSuccess(accountsList: List<ProductResponse>) {
        Log.i(RESULT, accountsList.toString())
        accountsListAdapter.updateAccountsList(accountsList)
    }



}