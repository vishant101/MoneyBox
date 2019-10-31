package com.example.minimoneybox.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
//            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "accounts").build()
//            @Suppress("UNCHECKED_CAST")
//            return MessageListViewModel(db.messagesDao()) as T
//        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}