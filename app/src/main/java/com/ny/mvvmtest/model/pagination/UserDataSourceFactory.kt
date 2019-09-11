package com.ny.mvvmtest.model.pagination

import androidx.lifecycle.MutableLiveData
import com.ny.mvvmtest.model.data.DataModel

class UserDataSourceFactory : androidx.paging.DataSource.Factory<Int,DataModel>(){

    private  lateinit var userDataSource : UserDataSource
    private  var mutableLiveData : MutableLiveData<UserDataSource> = MutableLiveData()

    override fun create(): androidx.paging.DataSource<Int, DataModel> {

        userDataSource =  UserDataSource()
        mutableLiveData.postValue(userDataSource)
        return  userDataSource
    }

    fun getMutableLiveData():MutableLiveData<UserDataSource>{
        return mutableLiveData
    }
}