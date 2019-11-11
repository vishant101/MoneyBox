package com.example.minimoneybox.views.accounts.account_element


import androidx.lifecycle.MutableLiveData
import com.example.minimoneybox.data.model.response.investorproducts.ProductResponse
import com.example.minimoneybox.utils.OnItemClickListener
import com.example.minimoneybox.utils.round
import com.example.minimoneybox.views.base.BaseViewModel

class AccountElementViewModel: BaseViewModel() {
    private val productResponse = MutableLiveData<ProductResponse>()
    val accountName = MutableLiveData<String>()
    val planValue = MutableLiveData<String>()
    val moneyBox = MutableLiveData<String>()
    lateinit var onClickListener: OnItemClickListener

    fun bind(productResponse: ProductResponse, onItemClickListener: OnItemClickListener){
        this.productResponse.value = productResponse
        this.onClickListener = onItemClickListener
        accountName.value = productResponse.Product.FriendlyName
        planValue.value = "£${round(productResponse.PlanValue, 2)}"
        moneyBox.value = "£${round(productResponse.Moneybox, 2)}"
    }

    fun itemClicked(){
        onClickListener.onItemClick(productResponse.value!!)
    }
}