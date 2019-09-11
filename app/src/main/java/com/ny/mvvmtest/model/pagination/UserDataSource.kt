package com.ny.mvvmtest.model.pagination

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.ny.mvvmtest.model.data.DataModel
import com.ny.mvvmtest.model.service.ProjectRepository





class UserDataSource : PageKeyedDataSource<Int, DataModel>() {

    val projectRepository = ProjectRepository.getInstance()

    //the size of a page that we want
    val per_page = 8
    private var page = 1

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, DataModel>
    ) {


        try {
            val call = projectRepository.callRetrofitBuilder()?.getAllUsers(page,per_page)?.execute()

            if(call?.isSuccessful!!){
                val responseOfuser = call.body()

                responseOfuser?.data?.let { callback.onResult(it, null, page + 1) }
            }
            else{
                Log.e("NetworkError->","Api Error")
            }
        }catch (e:Exception){
            e.printStackTrace()
        }



    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DataModel>) {

        try {
            val call = projectRepository.callRetrofitBuilder()?.getAllUsers(params.key,per_page)?.execute()

            if(call?.isSuccessful!!){
                val responseOfNextUser = call.body()
                if(responseOfNextUser?.data!=null && responseOfNextUser.data.isNotEmpty()) {
                    val adjacentKey = params.key+1
                    if(responseOfNextUser.data.isNotEmpty()) {
                        callback.onResult(responseOfNextUser.data, adjacentKey)
                    }
                }
            }
            else{
                Log.e("NetworkError->","Api Error")
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DataModel>) {



    }
}