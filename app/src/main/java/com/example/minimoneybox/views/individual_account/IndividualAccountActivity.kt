package com.example.minimoneybox.views.individual_account

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.minimoneybox.R
import com.example.minimoneybox.databinding.ActivityIndividualAccountBinding
import com.example.minimoneybox.injection.ViewModelFactory
import com.example.minimoneybox.views.accounts.AccountsActivity
import com.google.android.material.snackbar.Snackbar


class IndividualAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIndividualAccountBinding
    private lateinit var viewModel: IndividualAccountViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_individual_account)
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(IndividualAccountViewModel::class.java)
        viewModel.backPressed.observe(this, Observer {
                backPressed -> if(backPressed) openUserAccountsActivity()
        })
        viewModel.toastStatus.observe(this, Observer {
                toastStatus -> toastStatus?.let { Toast.makeText(this, R.string.add_error, Toast.LENGTH_LONG).show() }
        })
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun openUserAccountsActivity() {
        val intent = Intent(this@IndividualAccountActivity, AccountsActivity::class.java)
        startActivity(intent)
        finish()
    }
}