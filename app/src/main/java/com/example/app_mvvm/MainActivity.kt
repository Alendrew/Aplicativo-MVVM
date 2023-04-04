package com.example.app_mvvm

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_mvvm.rest.RetrofitService
import com.example.app_mvvm.adapters.MainAdapter
import com.example.app_mvvm.databinding.ActivityMainBinding
import com.example.app_mvvm.repositories.MainRepository

import com.example.app_mvvm.viewmodel.main.MainViewModel
import com.example.app_mvvm.viewmodel.main.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel : MainViewModel

    private val retrofitService = RetrofitService.getInstance()

    private val adapter = MainAdapter {
        openLink(it.link)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        viewModel = ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService)))[MainViewModel::class.java]
    }
    private fun initRecyclerView() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerview.adapter = this.adapter
    }
    override fun onStart() {
        super.onStart()

        viewModel.videoList.observe(this) { videos ->
            Log.i("TAG", "OnStart")
            adapter.setVideoList(videos)
        }

        viewModel.errorMessage.observe(this){ message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllVideos()
    }

    private fun openLink(link: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)
    }

}