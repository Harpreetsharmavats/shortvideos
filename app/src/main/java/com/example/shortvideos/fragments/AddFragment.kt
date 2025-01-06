package com.example.shortvideos.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shortvideos.Post.PostsActivity
import com.example.shortvideos.Post.VideosActivity
import com.example.shortvideos.R
import com.example.shortvideos.databinding.FragmentAddBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class AddFragment : BottomSheetDialogFragment() {
private lateinit var binding: FragmentAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentAddBinding.inflate(inflater, container, false)
        binding.post.setOnClickListener {
            activity?.startActivity(Intent(requireContext(),PostsActivity::class.java))
            activity?.finish()
        }
        binding.video.setOnClickListener {
            activity?.startActivity(Intent(requireContext(),VideosActivity::class.java))
            activity?.finish()
        }
        return binding.root
    }


    }
