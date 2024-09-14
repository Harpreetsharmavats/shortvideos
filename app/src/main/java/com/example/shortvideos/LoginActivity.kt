package com.example.shortvideos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shortvideos.Modals.User
import com.example.shortvideos.databinding.ActivityLoginBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        binding.loginbtn.setOnClickListener {
            if (binding.Email.editText?.text.toString().equals("") or
                binding.pass.editText?.text.toString().equals("")
                ) {Toast.makeText(
                    this@LoginActivity, "Please fill the details",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                val user=User(binding.Email.editText?.text.toString(),
                    binding.pass.editText?.text.toString())

                Firebase.auth.signInWithEmailAndPassword(user.email!!,user.password!!).addOnCompleteListener {
                    if (it.isSuccessful){
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                        finish()

                    }else{Toast.makeText(
                        this@LoginActivity, it.exception?.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()

                    }
                }
            }


        }
        binding.create.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
            finish()
        }

    }
}