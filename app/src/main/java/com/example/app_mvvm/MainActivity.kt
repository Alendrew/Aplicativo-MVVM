package com.example.app_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app_mvvm.rest.RetrofitService
import com.example.app_mvvm.adapters.MainAdapter
import com.example.app_mvvm.databinding.ActivityMainBinding
import com.example.app_mvvm.databinding.ResItemVideoBinding

import com.example.app_mvvm.viewmodel.main.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel : MainViewModel

    private val retrofitService = RetrofitService.getInstance()

    private val adapter = MainAdapter {

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}