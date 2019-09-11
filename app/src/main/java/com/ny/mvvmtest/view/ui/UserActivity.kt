package com.ny.mvvmtest.view.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ny.mvvmtest.R
import com.ny.mvvmtest.databinding.ActivityUserBinding
import com.ny.mvvmtest.viewmodel.UserViewModel

class UserActivity : AppCompatActivity() {

    private  var userViewModel = UserViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        setUpBinding()
    }

    private fun setUpBinding() {

        val binding : ActivityUserBinding =  DataBindingUtil.setContentView(this,R.layout.activity_user)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        binding.userViewModel = userViewModel
        setUpViewModel()

    }

    private fun setUpViewModel() {
        userViewModel.loading.set(View.VISIBLE)
        userViewModel.getUsers().observe(this, Observer {
            userViewModel.loading.set(View.GONE)
            if(it!=null && it.isNotEmpty()){
                userViewModel.setUsersInAdapter(it)
            }
            else{
                userViewModel.showEmpty.set(View.VISIBLE)
            }
        })
    }
}
