package com.example.help_me

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
        binding.signup.setOnClickListener {
            var email = binding.id.text.toString()
            var password = binding.password.text.toString()
            Log.d("상태","id: ${email} password: ${password}")
            createAccount(email,password)
        }
    }

    private fun createAccount(email: String, password: String){
        if(email.isNotEmpty() && password.isNotEmpty()){
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this) { task ->
                    if(task.isSuccessful){
                        toast("계정 생성 완료")
                        finish()
                    } else {
                        toast("계정 생성 실패")
                    }
                }
        }
    }

    private fun toast(m: String){
        Toast.makeText(this,m,Toast.LENGTH_SHORT).show()
    }

}
