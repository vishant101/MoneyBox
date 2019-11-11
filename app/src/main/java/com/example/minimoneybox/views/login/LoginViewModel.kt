package com.example.minimoneybox.views.login

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.minimoneybox.data.manager.AppDataManager
import com.example.minimoneybox.data.model.postbody.Login
import com.example.minimoneybox.data.model.response.login.UserProfile
import com.example.minimoneybox.network.AccountApi
import com.example.minimoneybox.utils.*
import com.example.minimoneybox.views.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.regex.Pattern
import javax.inject.Inject

class LoginViewModel: BaseViewModel() {

    @Inject
    lateinit var accountApi: AccountApi

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val emailErrorMessage:MutableLiveData<String> = MutableLiveData()
    val passwordErrorMessage:MutableLiveData<String> = MutableLiveData()
    val nameErrorMessage:MutableLiveData<String> = MutableLiveData()
    val loggedIn: MutableLiveData<Boolean> = MutableLiveData()

    var toastStatus = MutableLiveData<Boolean?>()
    val errorClickListener = View.OnClickListener { login() }

    private val enteredUserEmail = MutableLiveData<String>()
    private val enteredUserPassword = MutableLiveData<String>()
    private val enteredUserName = MutableLiveData<String>()

    private lateinit var subscription: Disposable

    override fun onCleared() {
        super.onCleared()
        if(::subscription.isInitialized) subscription.dispose()
    }

    fun login() {
        if (allFieldsValid()) {
            this.toastStatus.value = true

            subscription = Observable.fromCallable{}
                .concatMap {
                    accountApi.loginUser(
                        Login(
                            enteredUserEmail.value.toString(),
                            enteredUserPassword.value.toString(),
                            IFDA
                        )
                    )
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { setIsLoading(true) }
                .doOnTerminate { setIsLoading(false) }
                .subscribe(
                    { result -> onLoginSuccess(result) },
                    { error ->  onLoginError(error)}
                )
        }
    }

    private fun allFieldsValid() : Boolean {
        var allValid = true

        if (!Pattern.matches(EMAIL_REGEX, enteredUserEmail.value.toString())) {
            emailErrorMessage.value = EMAIL_ADDRESS_ERROR
            allValid = false
        } else {
            emailErrorMessage.value = null
        }

        if (!Pattern.matches(PASSWORD_REGEX, enteredUserPassword.value.toString())) {
            passwordErrorMessage.value = PASSWORD_ERROR
            allValid = false
        } else {
            passwordErrorMessage.value = null
        }

        if(!enteredUserName.value.isNullOrEmpty()) {
            if (!Pattern.matches(NAME_REGEX, enteredUserName.value.toString().trim())) {
                allValid = false
                nameErrorMessage.value = FULL_NAME_ERROR
            }
        } else {
            nameErrorMessage.value = null
        }

        return allValid
    }

    fun onEmailChange(s: CharSequence) {
        enteredUserEmail.value = s.toString()
    }

    fun onPasswordChange(s: CharSequence) {
        enteredUserPassword.value = s.toString()
    }

    fun onNameChange(s: CharSequence) {
        enteredUserName.value = s.toString()
    }

    private fun onLoginSuccess(result: UserProfile){
        Log.i(RESULT, result.toString())
        AppDataManager
            .updateUserInfo(
                result.Session.BearerToken,
                result.User.UserId,
                enteredUserName.value.toString(),
                enteredUserEmail.value.toString()
            )
        loggedIn.value =  true
    }

    private fun onLoginError(error: Throwable){
        Log.e(RESULT, error.toString())
        errorMessage.value = com.example.minimoneybox.R.string.login_error
    }
}