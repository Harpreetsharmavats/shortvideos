package com.example.shortvideos.Post

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.shortvideos.HomeActivity
import com.example.shortvideos.Modals.Post
import com.example.shortvideos.Modals.User
import com.example.shortvideos.R
import com.example.shortvideos.databinding.ActivityPostsBinding
import com.example.shortvideos.utils.POST
import com.example.shortvideos.utils.POST_FOLDER
import com.example.shortvideos.utils.USER_NODE
import com.example.shortvideos.utils.USER_PROFILE_FOLDER
import com.example.shortvideos.utils.uploadImage
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase

class PostsActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityPostsBinding.inflate(layoutInflater)
    }
    var imageUrl: String? = null
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, POST_FOLDER) { url ->
                if (url != null) {
                    binding.selectimage.setImageURI(uri)
                    imageUrl = url

                }
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        setSupportActionBar(binding.materialToolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar.setNavigationOnClickListener {
            startActivity(Intent(this@PostsActivity, HomeActivity::class.java))
            finish()
        }
        binding.selectimage.setOnClickListener {
            launcher.launch("image/*")
        }
        binding.cancelbtn.setOnClickListener {
            startActivity(Intent(this@PostsActivity, HomeActivity::class.java))
            finish()
        }

        binding.postbtn.setOnClickListener {
            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get()
                .addOnSuccessListener {

                    var user = it.toObject<User>()!!
                    val post:Post = Post(
                        postUrl = imageUrl!!,
                        caption = binding.caption.editText?.text.toString(),
                        uid =  Firebase.auth.currentUser!!.uid,
                        time = System.currentTimeMillis().toString()
                    )

                    Firebase.firestore.collection(POST).document().set(post).addOnSuccessListener {
                        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document()
                            .set(post).addOnSuccessListener {
                            startActivity(Intent(this@PostsActivity, HomeActivity::class.java))
                            finish()
                        }
                    }
                }
        }
    }




}