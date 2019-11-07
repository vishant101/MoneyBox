package com.example.minimoneybox.network

import com.example.minimoneybox.data.model.postbody.Login
import com.example.minimoneybox.data.model.response.investorproducts.InvestorProducts
import com.example.minimoneybox.data.model.response.login.UserProfile
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AccountApi {
    @POST("users/login")
    fun loginUser(@Body loginParameters : Login): Observable<UserProfile>

    @GET("/investorproducts")
    fun getAccounts(@Header("Authorization") accessToken: String?): Observable<InvestorProducts>
}