package com.example.shortvideos.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shortvideos.Modals.Video
import com.example.shortvideos.R
import com.example.shortvideos.databinding.VideoDgBinding
import com.squareup.picasso.Picasso

class VideoAdapter(var context: android.content.Context, var videolist:ArrayList<Video>):RecyclerView.Adapter<VideoAdapter.ViewHolder>(){
    inner class ViewHolder(var binding: VideoDgBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var binding = VideoDgBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return videolist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(videolist.get(position).profileLink).placeholder(R.drawable.user).into(holder.binding.profileimage)

        holder.binding.caption.setText(videolist.get(position).caption)
        holder.binding.videoView.setVideoPath(videolist.get(position).videoUrl)
        holder.binding.videoView.setOnPreparedListener {
            holder.binding.progressBar2.visibility=View.GONE
            holder.binding.videoView.start()
        }
    }
}