package com.example.app_mvvm.viewmodel.main

import androidx.lifecycle.VideoData
import androidx.lifecycle.MutableVideoData
import androidx.lifecycle.ViewModel
import com.example.app_mvvm.models.Video
import com.example.app_mvvm.repositories.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    val VideoList = MutableVideoData<List<Video>>()
    val errorMessage = MutableVideoData<String>()

    fun getAllVideos() {
        val request = repository.getAllVideos()
        request.enqueue(object : Callback<List<Video>>{
            override fun onResponse(call: Call<List<Video>>, response: Response<List<Video>>) {
                VideoList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Video>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }
}