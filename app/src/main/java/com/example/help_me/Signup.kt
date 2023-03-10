package com.example.help_me

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.help_me.databinding.ActivityLoginBinding
import com.example.help_me.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Signup : AppCompatActivity() {

    private var mbinding: ActivitySignupBinding? = null
    private val binding get() = mbinding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        if(binding.password1.text.toString() == binding.password.text.toString()){
            binding.signup.setOnClickListener {
                var email = binding.id.text.toString()
                var password = binding.password.text.toString()
//                Log.d("상태","id: ${email} password: ${password}")
//                Log.d("상태","1 : ${binding.password1.text.toString()} 2 : ${binding.password.text.toString()}")
                createAccount(email,password)
            }
        }
    }

    private fun createAccount(email: String, password: String){
        if(email.isNotEmpty() && password.isNotEmpty()){
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this) { task ->
                    if(task.isSuccessful){

                        toast("계정 생성 완료")
                        startActivity(Intent(this,Profile::class.java))
                        finish()
                    } else
                        toast("계정 생성 실패")
                    }
                }
        }
    private fun toast(m: String){
        Toast.makeText(this,m,Toast.LENGTH_SHORT).show()
    }
}
