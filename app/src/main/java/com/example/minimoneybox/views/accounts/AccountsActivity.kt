package com.example.minimoneybox.views.accounts

import android.content.Intent
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minimoneybox.R
import com.example.minimoneybox.data.model.response.investorproducts.ProductResponse
import com.example.minimoneybox.databinding.ActivityAccountsBinding
import com.example.minimoneybox.injection.ViewModelFactory
import com.example.minimoneybox.utils.PRODUCT_ID
import com.example.minimoneybox.views.individual_account.IndividualAccountActivity
import com.google.android.material.snackbar.Snackbar


class AccountsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccountsBinding
    private lateinit var viewModel: AccountsViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_accounts)
        binding.accountList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(AccountsViewModel::class.java)
        viewModel.selectedAccount.observe(this, Observer {
                selectedAccount -> if(selectedAccount != null) openIndividualAccountActivity()
        })
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }

    private fun openIndividualAccountActivity() {
        val intent = Intent(this@AccountsActivity, IndividualAccountActivity::class.java)
        startActivity(intent)
        finish()
    }
}