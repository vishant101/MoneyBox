package com.example.minimoneybox.views.accounts

import android.util.Log
import androidx.lifecycle.MutableLiveData
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
    val selectedAccount: MutableLiveData<ProductResponse> = MutableLiveData()

    private lateinit var subscription: Disposable
    private lateinit var itemClickSubscription: Disposable

    init {
        loadAccounts()
        setupItemClick()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
        itemClickSubscription.dispose()
    }

    private fun loadAccounts(){
        setIsLoading(true)

        subscription = Observable.fromCallable{ productDao.all }
            .concatMap {

                    dbAccountList ->
                if(dbAccountList.isEmpty())
                    accountApi.getAccounts( AppDataManager.getBearerToken() ).concatMap {
                            apiAccountList -> productDao.insertAll(*apiAccountList.ProductResponses.toTypedArray())
                        Observable.just(apiAccountList.ProductResponses)
                    }
                else
                    Observable.just(dbAccountList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { setIsLoading(true) }
            .doOnTerminate { setIsLoading(false) }
            .subscribe(
                { result -> onRetrieveAccountsSuccess(result) },
                { error -> Log.e(RESULT, error.toString()) }
            )
    }

    private fun onRetrieveAccountsSuccess(accountsList: List<ProductResponse>) {
        Log.i(RESULT, accountsList.toString())
        accountsListAdapter.updateAccountsList(accountsList)
    }

    private fun setupItemClick() {
        itemClickSubscription = accountsListAdapter.clickEvent
            .subscribe {
                selectedAccount.value = it
                AppDataManager.updateSelectedProductId(it.Id)
            }
    }


}