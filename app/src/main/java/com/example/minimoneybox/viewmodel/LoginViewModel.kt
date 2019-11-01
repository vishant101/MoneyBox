package com.example.minimoneybox.viewmodel

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.airbnb.lottie.LottieAnimationView
import com.example.minimoneybox.R
import com.example.minimoneybox.utils.*
import java.util.regex.Pattern

class LoginViewModel:BaseViewModel() {

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val emailErrorMessage:MutableLiveData<String> = MutableLiveData()
    val passwordErrorMessage:MutableLiveData<String> = MutableLiveData()
    val nameErrorMessage:MutableLiveData<String> = MutableLiveData()
    val errorClickListener = View.OnClickListener {  }

    var toastStatus = MutableLiveData<Boolean?>()

    private val enteredUserEmail = MutableLiveData<String>()
    private val enteredUserPassword = MutableLiveData<String>()
    private val enteredUserName = MutableLiveData<String>()

    lateinit var pigAnimation : LottieAnimationView


    fun login() {
        if (allFieldsValid()) {
            this.toastStatus.value = true
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
            if (!Pattern.matches(NAME_REGEX, enteredUserName.value.toString())) {
                allValid = false
                nameErrorMessage.value = FULLNAME_ERROR
            }
        } else {
            nameErrorMessage.value = null
        }

        return allValid
    }

    private fun setupAnimation() {
        pigAnimation.setMinAndMaxFrame(firstAnim.first, firstAnim.second)
        pigAnimation.playAnimation()

        pigAnimation.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                pigAnimation.setMinAndMaxFrame(secondAnim.first, secondAnim.second)
                pigAnimation.playAnimation()
            }
        })
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

    companion object {
        val firstAnim = 0 to 109
        val secondAnim = 131 to 158
    }
}