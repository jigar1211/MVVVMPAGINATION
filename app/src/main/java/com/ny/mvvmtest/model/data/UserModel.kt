package com.ny.mvvmtest.model.data

data class UserModel(
    val `data`: List<DataModel>,
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int
)