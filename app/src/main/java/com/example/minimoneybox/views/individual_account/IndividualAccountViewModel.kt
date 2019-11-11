package com.example.minimoneybox.views.individual_account

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.minimoneybox.data.database.ProductDao
import com.example.minimoneybox.data.manager.AppDataManager
import com.example.minimoneybox.data.model.response.investorproducts.ProductResponse
import com.example.minimoneybox.network.AccountApi
import com.example.minimoneybox.utils.OnItemClickListener
import com.example.minimoneybox.utils.PRODUCT_ID
import com.example.minimoneybox.utils.RESULT
import com.example.minimoneybox.views.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class IndividualAccountViewModel(private val productDao: ProductDao): BaseViewModel() {
    @Inject
    lateinit var accountApi: AccountApi
    private lateinit var subscription: Disposable

    val accountName = MutableLiveData<String>()
    val planValue = MutableLiveData<Float>()
    val moneyBox = MutableLiveData<Float>()
    lateinit var onClickListener: OnItemClickListener

    init {
        loadAccount(AppDataManager.currentProductId)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadAccount(Id: Int){
        setIsLoading(true)

        subscription = Observable.fromCallable{ productDao.getProduct(Id) }
            .concatMap {
                    dbProduct -> Observable.just(dbProduct)
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

    private fun onRetrieveAccountsSuccess(account: ProductResponse) {
        Log.i(RESULT, account.toString())
        accountName.value = account.Product.FriendlyName
        planValue.value = account.PlanValue
        moneyBox.value = account.Moneybox
        setIsLoading(false)
    }




}