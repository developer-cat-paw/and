package com.example.help_me

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.help_me.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Profile : AppCompatActivity() {
    private var mbinding: ActivityProfileBinding ?= null
    private val binding get() = mbinding!!

    private var auth : FirebaseAuth?= null

    private val OPEN_GALLERY = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.profileimageedit.setOnClickListener{
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent, OPEN_GALLERY)
        }
        binding.logout.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            auth?.signOut()
        }
        binding.saveProfile.setOnClickListener {
            Log.d("상태","onclick_saveProfile_button")
            val text: String = binding.introduction.text.toString()
            val name: String = binding.name.text.toString()
            val ios: Boolean
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            if(requestCode == OPEN_GALLERY){
                var profileImageUrl: Uri ?= data?.data

                try {
                    Glide.with(this)
                        .load(profileImageUrl)
                        .circleCrop()
                        .error(R.drawable.profile_img)
                        .into(binding.profileimage)
                } catch (e:Exception){
                    Log.d("상태","${e.message}")
                }
            }
        } else {
            Log.d("상태","something wrong")
        }
    }
}