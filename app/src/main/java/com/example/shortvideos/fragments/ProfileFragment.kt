package com.example.shortvideos.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shortvideos.Modals.User
import com.example.shortvideos.R
import com.example.shortvideos.SignupActivity
import com.example.shortvideos.adapters.ViewPagerAdapter
import com.example.shortvideos.databinding.FragmentProfileBinding
import com.example.shortvideos.utils.USER_NODE
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso


class ProfileFragment : Fragment() {
   private lateinit var binding :FragmentProfileBinding
   private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.editprofile.setOnClickListener {
            var intent=Intent(activity, SignupActivity::class.java)
            intent.putExtra("MODE",1)
            activity?.startActivity(intent)
            activity?.finish()
        }
        viewPagerAdapter=ViewPagerAdapter(requireActivity().supportFragmentManager)
        viewPagerAdapter.addFragments(MyPostFragment(), "My Post")
        viewPagerAdapter.addFragments(MyVideosFragment(), "My Videos")
        binding.viewpager.adapter=viewPagerAdapter
        binding.tablayout.setupWithViewPager(binding.viewpager)

        return binding.root
    }




    override fun onStart() {
        super.onStart()
        Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
            val user:User = it.toObject<User>()!!
            binding.name.text=user.name
            binding.bio.text=user.email
            if (!user.image.isNullOrEmpty()){
                Picasso.get().load(user.image).into(binding.profileimage
                )
            }

        }
    }
}




