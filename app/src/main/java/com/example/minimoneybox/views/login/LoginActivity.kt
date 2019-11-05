package com.example.minimoneybox.views.login


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.minimoneybox.R
import com.example.minimoneybox.databinding.ActivityLoginBinding
import com.example.minimoneybox.injection.ViewModelFactory
import com.example.minimoneybox.views.useraccounts.AccountsActivity
import com.google.android.material.snackbar.Snackbar


/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity(){
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(LoginViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
                errorMessage -> if(errorMessage != null) showError(errorMessage) else hideError()
        })
        viewModel.toastStatus.observe(this, Observer {
                toastStatus -> toastStatus?.let { Toast.makeText(this, R.string.input_valid, Toast.LENGTH_LONG).show() }
        })
        viewModel.loggedIn.observe(this, Observer {
                loggedIn -> if(loggedIn) openUserAccountsActivity()
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

    private fun openUserAccountsActivity() {
        val intent = Intent(this@LoginActivity, AccountsActivity::class.java)
        startActivity(intent)
        finish()
    }
}
