package com.ny.mvvmtest.viewmodel

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.DiffUtil
import com.ny.mvvmtest.model.data.DataModel
import com.ny.mvvmtest.model.pagination.UserDataSource
import com.ny.mvvmtest.model.pagination.UserDataSourceFactory
import com.ny.mvvmtest.view.adapter.UserAdapter
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class UserViewModel : ViewModel() {

    var userList: LiveData<PagedList<DataModel>>
    var  userAdapter : UserAdapter = UserAdapter(this)
    var loading: ObservableInt = ObservableInt(View.GONE)
    var showEmpty: ObservableInt = ObservableInt(View.GONE)
    private val userDataResourceFactory:UserDataSourceFactory = UserDataSourceFactory()
    var userDatSourceLiveData : MutableLiveData<UserDataSource>  = userDataResourceFactory.getMutableLiveData()
    var executor: Executor

    init {

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(4)
            .setPageSize(2)
            .setPrefetchDistance(2)
            .build()
        executor = Executors.newFixedThreadPool(5)

        userList = LivePagedListBuilder<Int, DataModel>(userDataResourceFactory, config)
            .setFetchExecutor(executor)
            .build()
    }



    fun getAdapter():UserAdapter{

        return  userAdapter
    }

    fun setUsersInAdapter(useList: PagedList<DataModel>) {

        userAdapter.submitList(useList)
        userAdapter.notifyDataSetChanged()

    }



    fun getUsers():LiveData<PagedList<DataModel>>{

        return userList
    }



    val CALLBACK: DiffUtil.ItemCallback<DataModel> = object : DiffUtil.ItemCallback<DataModel>() {
        override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
            return true
        }

        override fun areItemsTheSame(dataModel: DataModel, t1: DataModel): Boolean {
            return dataModel.id ==  t1.id
        }

    }
}