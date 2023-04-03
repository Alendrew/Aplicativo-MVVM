package com.example.app_mvvm.repositories

import com.example.app_mvvm.rest.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllVideos() = retrofitService.getAllVideos()

}