package com.example.shortvideos.Post

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.shortvideos.HomeActivity
import com.example.shortvideos.Modals.User
import com.example.shortvideos.Modals.Video
import com.example.shortvideos.databinding.ActivityVideosBinding
import com.example.shortvideos.utils.USER_NODE
import com.example.shortvideos.utils.VIDEO
import com.example.shortvideos.utils.VIDEO_FOLDER
import com.example.shortvideos.utils.uploadVideo
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase

class VideosActivity() : AppCompatActivity(){
    val binding by lazy {
        ActivityVideosBinding.inflate(layoutInflater)
    }
    lateinit var progressDialog:ProgressDialog
    private lateinit var videoUrl: String
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadVideo(uri, VIDEO_FOLDER,progressDialog) { url ->
                if (url != null) {

                    videoUrl = url

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
            startActivity(Intent(this@VideosActivity, HomeActivity::class.java))
            finish()
        }

        progressDialog=ProgressDialog(this)
        binding.selectvideo.setOnClickListener {
            launcher.launch("video/*")
        }
        binding.cancelbtn.setOnClickListener {
            startActivity(Intent(this@VideosActivity, HomeActivity::class.java))
            finish()
        }
        binding.postbtn.setOnClickListener {
            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
                var user:User=it.toObject<User>()!!
                val video: Video = Video(videoUrl!!, binding.caption.editText?.text.toString(),user.image!!)

                Firebase.firestore.collection(VIDEO).document().set(video).addOnSuccessListener {
                    Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ VIDEO).document().set(video)
                        .addOnSuccessListener {
                            startActivity(Intent(this@VideosActivity, HomeActivity::class.java))
                            finish()
                        }
                }
            }


        }
    }
}
