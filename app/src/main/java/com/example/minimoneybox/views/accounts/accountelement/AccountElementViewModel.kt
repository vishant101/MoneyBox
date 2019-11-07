package com.example.minimoneybox.views.accounts.accountelement


import androidx.lifecycle.MutableLiveData
import com.example.minimoneybox.data.model.response.investorproducts.ProductResponse
import com.example.minimoneybox.views.base.BaseViewModel

class AccountElementViewModel: BaseViewModel() {
    private val productResponse = MutableLiveData<ProductResponse>()
    val accountName = MutableLiveData<String>()
    val planValue = MutableLiveData<String>()
    val moneyBox = MutableLiveData<String>()

    fun bind(productResponse: ProductResponse){
        accountName.value = productResponse.Product.FriendlyName
        planValue.value = productResponse.PlanValue.toString()
        moneyBox.value = productResponse.Moneybox.toString()
    }
}