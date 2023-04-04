package com.example.app_mvvm.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.app_mvvm.R
import com.example.app_mvvm.databinding.ResItemVideoBinding
import com.example.app_mvvm.models.Video

class MainAdapter(private val onItemClicked: (Video) -> Unit) : Adapter<RecyclerView.ViewHolder>() {

    private lateinit var binding: ResItemVideoBinding
    private var items : List<Video> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ResItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val view = binding.root
        return VideoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is VideoViewHolder -> {
                holder.bind(items[position], onItemClicked)
            }
        }

    }

    fun setVideoList(items : List<Video>) {
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    class VideoViewHolder constructor(
        itemView: View
    ):RecyclerView.ViewHolder(itemView){
        private val binding: ResItemVideoBinding = ResItemVideoBinding.bind(itemView)

        fun bind(Video: Video, onItemClicked: (Video) -> Unit){
            binding.title.text = Video.title
            binding.author.text = Video.author

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(Video.thumbnailUrl)
                .into(binding.thumbnail)

            itemView.setOnClickListener{
                onItemClicked(Video)
            }

        }
    }
}
