package com.example.shortvideos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.shortvideos.Modals.Post
import com.example.shortvideos.Modals.Video
import com.example.shortvideos.R
import com.example.shortvideos.adapters.MyPostRvAdapter
import com.example.shortvideos.adapters.MyVideosAdapter
import com.example.shortvideos.databinding.FragmentMyVideosBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import java.util.zip.Inflater


class MyVideosFragment : Fragment() {
    private lateinit var binding: FragmentMyVideosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentMyVideosBinding.inflate(inflater, container, false)
        var videoList=ArrayList<Video>()
        var adapter= MyVideosAdapter(requireContext(),videoList)
        binding.rv.layoutManager= StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.rv.adapter=adapter
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
            var tempList= arrayListOf<Video>()
            for (i in it.documents){
                var video: Video =i.toObject<Video>()!!
                tempList.add(video)
            }
            videoList.addAll(tempList)
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }

    companion object {

            }
    }
