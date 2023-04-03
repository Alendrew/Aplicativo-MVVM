package com.example.app_mvvm.rest

import com.example.app_mvvm.models.Video
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("Videos")
    fun getAllVideos(): Call<List<Video>>

    companion object {
        private val retrofitService : RetrofitService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://6422ff9a001cb9fc20358388.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(RetrofitService::class.java)
        }
        fun getInstance() : RetrofitService {
            return retrofitService
        }
    }
}