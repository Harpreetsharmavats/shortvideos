package com.example.shortvideos.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shortvideos.Modals.User
import com.example.shortvideos.R
import com.example.shortvideos.databinding.FollowRvBinding
import com.squareup.picasso.Picasso

class FollowAdapter(var context: Context, var followList: ArrayList<User>):RecyclerView.Adapter<FollowAdapter.ViewHolder>(){
    inner class ViewHolder(var binding: FollowRvBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding=FollowRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return followList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context).load(followList.get(position).image).placeholder(R.drawable.user).into(holder.binding.profileimage)
        holder.binding.name.text=followList.get(position).name

    }
}