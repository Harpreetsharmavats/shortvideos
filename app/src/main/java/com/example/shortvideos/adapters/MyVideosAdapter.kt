package com.example.shortvideos.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.shortvideos.Modals.Video
import com.example.shortvideos.databinding.MyPostRvDesignBinding


class MyVideosAdapter(var context: android.content.Context, var videoList: ArrayList<Video>) :
    RecyclerView.Adapter<MyVideosAdapter.ViewHolder>() {

    inner class ViewHolder(var binding: MyPostRvDesignBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = MyPostRvDesignBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context)
            .load(videoList.get(position).videoUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.binding.postimage)
    }
}