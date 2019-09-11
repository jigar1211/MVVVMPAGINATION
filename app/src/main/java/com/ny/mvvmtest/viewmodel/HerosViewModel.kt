package com.ny.mvvmtest.viewmodel

import android.content.Intent
import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ny.mvvmtest.model.data.MarvelModel
import com.ny.mvvmtest.model.service.ProjectRepository
import com.ny.mvvmtest.view.adapter.HerosAdapter
import com.ny.mvvmtest.view.ui.MainActivity
import com.ny.mvvmtest.view.ui.UserActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class HerosViewModel : ViewModel(){

    var herosList = MutableLiveData<List<MarvelModel>>()
    val projectRepository = ProjectRepository.getInstance()
    var  herosAdapter : HerosAdapter = HerosAdapter(com.ny.mvvmtest.R.layout.view_heroes,this)
    var loading: ObservableInt = ObservableInt(View.GONE)
    var showEmpty: ObservableInt = ObservableInt(View.GONE)
    var imageUrl : String = ""

    fun getAdapter(): HerosAdapter {
        return herosAdapter
    }

    fun setHerosInAdapter(heroList: List<MarvelModel>) {
        herosAdapter.setHeroList(heroList)
        herosAdapter.notifyDataSetChanged()
    }

    fun getHeroes(): LiveData<List<MarvelModel>> {
        val call = projectRepository.callRetrofitBuilder()?.getHeros()
        call?.enqueue(object : Callback<List<MarvelModel>> {
            override fun onResponse(call: Call<List<MarvelModel>>, response: Response<List<MarvelModel>>) {
                herosList.value = response.body()
            }
            override fun onFailure(call: Call<List<MarvelModel>>, t: Throwable) {
                herosList.value = null
            }
        })
        return herosList
    }

    fun getTitel(index: Int?): String {
        return if(index!=null && herosList.value!!.isNotEmpty()){herosList.value?.get(index)?.name.toString()}else ""
    }


    fun getImageURL(index: Int?) {

        imageUrl = if(index!=null && herosList.value!!.isNotEmpty()){

            herosList.value?.get(index)?.imageurl.toString()

        }else  ""
    }

    fun onItemClick(index: Int?,activity: MainActivity){
        activity.startActivity(Intent(activity, UserActivity::class.java))
    }


}