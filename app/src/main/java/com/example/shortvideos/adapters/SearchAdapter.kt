package com.example.shortvideos.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shortvideos.Modals.User
import com.example.shortvideos.R
import com.example.shortvideos.databinding.SearchRvBinding
import com.example.shortvideos.utils.FOLLOW
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SearchAdapter(var context: android.content.Context, var userList: ArrayList<User>) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: SearchRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = SearchRvBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var isfollow = false
        Glide.with(context).load(userList.get(position).image).placeholder(R.drawable.user)
            .into(holder.binding.profileimage)
        holder.binding.name.text = userList.get(position).name

        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + FOLLOW)
            .whereEqualTo("email", userList.get(position).email).get().addOnSuccessListener {
            if (it.documents.size==0) {
                isfollow = false
            } else {
                holder.binding.follow.text = "Unfollow"
                isfollow = true
            }
        }

        holder.binding.follow.setOnClickListener {
            if (isfollow) {
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + FOLLOW)
                    .whereEqualTo("email", userList.get(position).email).get().addOnSuccessListener {
                        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + FOLLOW).document(it.documents.get(0).id).delete()
                        holder.binding.follow.text="Follow"
                        isfollow=false
                    }

            } else {
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + FOLLOW).document()
                    .set(userList.get(position))
                holder.binding.follow.text = "Unfollow"
                isfollow=true
            }
        }
    }
}