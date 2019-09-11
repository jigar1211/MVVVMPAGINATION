package com.ny.mvvmtest.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.ny.mvvmtest.model.data.MarvelModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class HerosViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    var herosList = MutableLiveData<List<MarvelModel>>()

    @Mock
    lateinit var herosViewModel : HerosViewModel

    @Before
    fun setUp() {
        herosViewModel = Mockito.spy(HerosViewModel::class.java)
    }

    @Test
    fun loadHeroes() {
        `when`(herosViewModel.getHeroes()).thenReturn(herosList)
    }

    @Test
    fun getTitelTest(){

        if(herosList.value?.isNotEmpty()!!)
       for(i in 0..herosList.value?.size!!){

        }

    }
}