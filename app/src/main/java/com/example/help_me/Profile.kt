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
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Profile : AppCompatActivity() {
    private var mbinding: ActivityProfileBinding ?= null
    private val binding get() = mbinding!!

    private var auth : FirebaseAuth?= null

    private val database = Firebase.database("https://sexy-b53e8-default-rtdb.asia-southeast1.firebasedatabase.app/")
    private val name = database.getReference("name")
    private val text = database.getReference("text")
    private val profileImage = database.getReference("profileImage")
//    lateinit private var profileImageUrl

    private val OPEN_GALLERY = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
//        binding.profileimage.setImageURI(profileImageUrl)

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
//            Log.d("상태","onclick_saveProfile_button")
            name.setValue(binding.name.text.toString())
            text.setValue(binding.introduction.text.toString())
            Log.d("상태","saveProfile")
            Log.d("상태","name : $name text : $text profileImage : $profileImage")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            if(requestCode == OPEN_GALLERY){
                var profileImageUrl: Uri ?= data?.data
                profileImage.setValue(profileImageUrl.toString())

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