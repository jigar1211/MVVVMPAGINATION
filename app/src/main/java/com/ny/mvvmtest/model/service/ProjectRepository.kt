package com.ny.mvvmtest.model.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ProjectRepository {

  //  private val BASEURL = "https://simplifiedcoding.net/demos/"
    private val BASEURL = "https://reqres.in/api/"
    private var apiInterface: APIinterface? = null

    companion object{

        private var projectRepository: ProjectRepository? = null

        @Synchronized
        @JvmStatic
        fun getInstance(): ProjectRepository {
            if (projectRepository == null) {
                projectRepository = ProjectRepository()
            }
            return projectRepository!!
        }

    }

    fun callRetrofitBuilder(): APIinterface? {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASEURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        apiInterface = retrofit.create(APIinterface::class.java)

        return apiInterface
    }
}