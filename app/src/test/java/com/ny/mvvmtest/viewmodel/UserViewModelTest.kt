package com.ny.mvvmtest.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ny.mvvmtest.model.data.DataModel
import com.ny.mvvmtest.model.data.UserModel
import com.ny.mvvmtest.model.pagination.UserDataSource
import com.ny.mvvmtest.model.pagination.UserDataSourceFactory
import com.ny.mvvmtest.model.service.ProjectRepository
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class UserViewModelTest {


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var listOfDataModel : LiveData<List<DataModel>>


    @Test
    fun getUserList() {

        val listOfDataModel = generateDataModel()
        val userDataResourceFactory: UserDataSourceFactory = UserDataSourceFactory()
        val userDatSourceLiveData : MutableLiveData<UserDataSource>  = userDataResourceFactory.getMutableLiveData()

   //     Mockito.`when`(userDatSourceLiveData).thenReturn(listOfDataModel)


    }

    private fun generateDataModel(): LiveData<List<DataModel>> {

        val listOfdataModel = MutableLiveData<List<DataModel>>()
        val projectRepository = ProjectRepository.getInstance()

        val call = projectRepository.callRetrofitBuilder()?.getAllUsers(1, 8)
        call?.enqueue(object : Callback<UserModel> {
            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                listOfdataModel.value = null
            }

            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {

                val responseOfUser = response.body()
                listOfdataModel.value = responseOfUser?.data
            }
        })

        return listOfdataModel
    }
}