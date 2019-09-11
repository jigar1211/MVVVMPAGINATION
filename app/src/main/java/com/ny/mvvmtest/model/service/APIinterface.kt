package com.ny.mvvmtest.model.service

import com.ny.mvvmtest.model.data.MarvelModel
import com.ny.mvvmtest.model.data.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIinterface {

    @GET("Marvel")
    fun getHeros(): Call<List<MarvelModel>>

    @GET("users")
    fun getAllUsers(@Query("page") page: Int, @Query("per_page") per_page: Int): Call<UserModel>
}