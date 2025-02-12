package com.example.shortvideos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shortvideos.Modals.Post
import com.example.shortvideos.Modals.User
import com.example.shortvideos.R
import com.example.shortvideos.adapters.FollowAdapter
import com.example.shortvideos.adapters.PostAdapter
import com.example.shortvideos.databinding.FragmentHomeBinding
import com.example.shortvideos.utils.FOLLOW
import com.example.shortvideos.utils.POST
import com.example.shortvideos.utils.USER_NODE
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {
   private lateinit var binding: FragmentHomeBinding
   private  var postList=ArrayList<Post>()
   private lateinit var adapter: PostAdapter
   private var followList=ArrayList<User>()
    private lateinit var followAdapter: FollowAdapter
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
        binding=FragmentHomeBinding.inflate(inflater, container, false)
        adapter=PostAdapter(requireContext(),postList)
        binding.rv.layoutManager=LinearLayoutManager(requireContext())
        binding.rv.adapter=adapter

        followAdapter=FollowAdapter(requireContext(),followList)
        binding.followedrv.layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.followedrv.adapter=followAdapter


        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.materialToolbar2)

        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + FOLLOW).get().addOnSuccessListener {
            var tempList=ArrayList<User>()
            followList.clear()
            for (i in it.documents){
                var user:User=i.toObject<User>()!!
                tempList.add(user)
            }
            followList.addAll(tempList)
            followAdapter.notifyDataSetChanged()
        }

        Firebase.firestore.collection(POST).get().addOnSuccessListener {
            var tempList=ArrayList<Post>()
            postList.clear()
            for (i in it.documents){
                var post:Post=i.toObject<Post>()!!
                tempList.add(post)



            }
            postList.addAll(tempList)
            postList.reverse()
            adapter.notifyDataSetChanged()
        }

        return binding.root
    }

    companion object {

                }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onStart() {
        super.onStart()
        Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
            val user:User = it.toObject<User>()!!

            if (!user.image.isNullOrEmpty()){
                Picasso.get().load(user.image).into(binding.imageView3)
            }

        }
    }
            }
