package com.ny.mvvmtest.view.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ny.mvvmtest.R
import com.ny.mvvmtest.databinding.ActivityMainBinding
import com.ny.mvvmtest.viewmodel.HerosViewModel


class MainActivity : AppCompatActivity() {

    private var herosViewModel: HerosViewModel = HerosViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBindings()
    }

    private fun setupBindings() {
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        herosViewModel = ViewModelProviders.of(this).get(HerosViewModel::class.java)
        binding.heroViewModel = herosViewModel
        setUpViewModel()
    }

    private fun setUpViewModel() {
        herosViewModel.loading.set(View.VISIBLE)
        herosViewModel.getHeroes().observe(this, Observer {
            herosViewModel.loading.set(View.GONE)
            if(it!=null && it.isNotEmpty()){
                herosViewModel.setHerosInAdapter(it)
            }else{
                herosViewModel.showEmpty.set(View.VISIBLE)
            }
        })
    }
}


