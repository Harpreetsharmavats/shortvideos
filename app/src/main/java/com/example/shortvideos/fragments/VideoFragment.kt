package com.example.shortvideos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shortvideos.Modals.Video
import com.example.shortvideos.adapters.VideoAdapter
import com.example.shortvideos.databinding.FragmentVideoBinding
import com.example.shortvideos.utils.VIDEO
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase


class VideoFragment : Fragment() {
    private lateinit var binding: FragmentVideoBinding
    private lateinit var adapter: VideoAdapter
    var videoList=ArrayList<Video>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVideoBinding.inflate(inflater, container, false)
        adapter=VideoAdapter(requireContext(),videoList)
        binding.viewpager.adapter=adapter
        Firebase.firestore.collection(VIDEO).get().addOnSuccessListener {
            var tempList=ArrayList<Video>()
            videoList.clear()

            for (i in it.documents){
                var video=i.toObject<Video>()!!
                tempList.add(video)


            }
            videoList.addAll(tempList)
            videoList.reverse()
            adapter.notifyDataSetChanged()
        }

        return binding.root


    }
}
