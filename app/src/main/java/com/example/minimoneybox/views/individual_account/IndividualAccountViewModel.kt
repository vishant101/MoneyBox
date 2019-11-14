package com.example.minimoneybox.views.individual_account

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.minimoneybox.data.database.ProductDao
import com.example.minimoneybox.data.manager.AppDataManager
import com.example.minimoneybox.data.model.postbody.OneOffPayments
import com.example.minimoneybox.data.model.response.investorproducts.ProductResponse
import com.example.minimoneybox.data.model.response.oneoffpayments.OneOffPaymentResponse
import com.example.minimoneybox.network.AccountApi
import com.example.minimoneybox.utils.OnItemClickListener
import com.example.minimoneybox.utils.QUICK_ADD_AMOUNT
import com.example.minimoneybox.utils.RESULT
import com.example.minimoneybox.utils.round
import com.example.minimoneybox.views.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.math.BigDecimal
import javax.inject.Inject


class IndividualAccountViewModel(private val productDao: ProductDao): BaseViewModel() {
    @Inject
    lateinit var accountApi: AccountApi
    private var subscription: Disposable? = null
    private var addSubscription: Disposable? = null
    private var dataUpdateSubscription: Disposable? = null

    val backPressed = MutableLiveData<Boolean>()
    var toastStatus = MutableLiveData<Boolean?>()

    val accountName = MutableLiveData<String>()
    val productResponse = MutableLiveData<ProductResponse>()
    val planValue = MutableLiveData<String>()
    val moneyBox = MutableLiveData<String>()
    val collections = MutableLiveData<String>()
    val quickAddString = MutableLiveData<String>()
    val addButtonEnabled = ObservableBoolean()


    init {
        loadAccount(AppDataManager.currentProductId)
    }

    override fun onCleared() {
        super.onCleared()
        subscription?.dispose()
        addSubscription?.dispose()
        dataUpdateSubscription?.dispose()
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
        productResponse.value = account
        accountName.value = account.Product.FriendlyName
        planValue.value = "£${round(account.PlanValue, 2)}"
        moneyBox.value = "£${round(account.Moneybox, 2)}"
        quickAddString.value = "+£$QUICK_ADD_AMOUNT"
        collections.value = account.CollectionDayMessage
        setIsLoading(false)
        addButtonEnabled.set(true)
    }

    fun addButtonClicked(){
       val productResponseVal = productResponse.value
       quickAdd(
           QUICK_ADD_AMOUNT,
           productResponseVal!!.Id
       )
    }

    fun backButtonClicked(){
        backPressed.value = true
    }

    private fun quickAdd(amount: Int, productId: Int){
        addSubscription = Observable.fromCallable { }
            .concatMap { accountApi.oneOffPayment(
                AppDataManager.getBearerToken(),
                OneOffPayments(amount, productId)
            ) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onAddStart() }
            .doOnTerminate { onAddFinish() }
            .subscribe(
                { result -> onAddSuccess(result) },
                { error -> onAddError(error) }
            )
    }

    private fun updateDataBaseMoneyBox(amount: Float, productId: Int){
        dataUpdateSubscription = Observable.fromCallable{ productDao.updateMoneyBox(productId, amount) }
            .concatMap {
                    dbProduct -> Observable.just(dbProduct)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { setIsLoading(true) }
            .doOnTerminate { setIsLoading(false) }
            .subscribe(
                { result -> Log.i(RESULT, result.toString()) },
                { error -> onAddError(error) }
            )
    }

    private fun onAddStart(){
        addButtonEnabled.set(false)
        setIsLoading(true)
    }

    private fun onAddFinish(){
        addButtonEnabled.set(true)
        setIsLoading(false)
    }

    private fun onAddSuccess(result: OneOffPaymentResponse){
        val newValue = QUICK_ADD_AMOUNT.toFloat() + productResponse.value!!.Moneybox
        updateDataBaseMoneyBox(newValue, AppDataManager.currentProductId)
        loadAccount(AppDataManager.currentProductId)
    }

    private fun onAddError(error: Throwable){
        Log.e(RESULT, error.toString())
        toastStatus.value =  true
    }
}