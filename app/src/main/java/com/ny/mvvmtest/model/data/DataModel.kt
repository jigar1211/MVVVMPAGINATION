package com.ny.mvvmtest.model.data

import androidx.databinding.BaseObservable

data class DataModel
    (
    val avatar: String,
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String
    ): BaseObservable()
