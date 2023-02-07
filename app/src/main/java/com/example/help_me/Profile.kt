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

class Profile : AppCompatActivity() {
    private var mbinding: ActivityProfileBinding ?= null
    private val binding get() = mbinding!!

    private val OPEN_GALLERY = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.profileimageedit.setOnClickListener{
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent, OPEN_GALLERY)
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