package com.example.app_mvvm

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
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

    private val adapter = MainAdapter { video ->
        openLink(video.link)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)

        viewModel = ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService)))[MainViewModel::class.java]

        binding.recyclerview.adapter = adapter
    }

    override fun onStart() {
        super.onStart()

        viewModel.videoList.observe(this, Observer { videos ->
            Log.i("TAG", "OnStart")
            adapter.setDataSet(videos)
        })

        viewModel.errorMessage.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
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