package com.example.minimoneybox.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.minimoneybox.viewmodel.LoginViewModel
import com.google.android.material.textfield.TextInputLayout




@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}


@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}


@BindingAdapter("errorMessage")
fun setErrorMessage(view: TextInputLayout, errorMessage:  MutableLiveData<String>?) {
    view.error = errorMessage!!.value
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
@BindingAdapter("animate")
fun animate(target: LottieAnimationView, isVisible: Boolean) {
    val firstAnim = 0 to 109
    val secondAnim = 131 to 158

    target.setMinAndMaxFrame(firstAnim.first, firstAnim.second)
    target.playAnimation()

    target.addAnimatorListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) {
            target.setMinAndMaxFrame(secondAnim.first, secondAnim.second)
            target.playAnimation()
        }
    })
}