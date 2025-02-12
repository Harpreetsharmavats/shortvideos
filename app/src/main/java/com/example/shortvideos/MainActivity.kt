package com.example.shortvideos

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                if (FirebaseAuth.getInstance().currentUser == null)
                    startActivity(Intent(this, SignupActivity::class.java))
                else
                    startActivity(Intent(this,HomeActivity::class.java))

                finish()
            }, 2000
        )
    }
}