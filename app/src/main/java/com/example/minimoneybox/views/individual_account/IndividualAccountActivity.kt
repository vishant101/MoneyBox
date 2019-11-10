package com.example.minimoneybox.views.individual_account

import android.os.Bundle
import android.util.Log
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.minimoneybox.R
import com.example.minimoneybox.databinding.ActivityIndividualAccountBinding
import com.example.minimoneybox.injection.ViewModelFactory
import com.example.minimoneybox.utils.PRODUCT_ID
import com.google.android.material.snackbar.Snackbar


class IndividualAccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIndividualAccountBinding
    private lateinit var viewModel: IndividualAccountViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_individual_account)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(IndividualAccountViewModel::class.java)
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
}