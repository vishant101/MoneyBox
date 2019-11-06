package com.example.minimoneybox.network

import com.example.minimoneybox.data.model.LoginParameters
import com.example.minimoneybox.data.model.UserProfile
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AccountApi {
    @POST("users/login")
    fun loginUser(@Body loginParameters : LoginParameters): Observable<UserProfile>

    @GET("/investorproducts")
    fun getAccounts(): Observable<UserProfile>
}