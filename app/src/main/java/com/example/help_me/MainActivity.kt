package com.example.help_me

import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.help_me.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

class MainActivity : AppCompatActivity() {
    private var mbinding: ActivityMainBinding ?= null
    private val binding get() = mbinding!!

    private var auth : FirebaseAuth ?= null
    private lateinit var databaseRef: DatabaseReference
    lateinit var storage: FirebaseStorage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        storage = Firebase.storage

        val storage = FirebaseStorage.getInstance("gs://sexy-b53e8.appspot.com")
        val storageRef = storage.reference
        storageRef.child("images/test.jpg").downloadUrl.addOnSuccessListener { uri -> //이미지 로드 성공시
            Glide.with(applicationContext)
                .load(uri)
                .into(binding.img)
            Toast.makeText(this,"성공",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { //이미지 로드 실패시
            Toast.makeText(applicationContext, "실패", Toast.LENGTH_SHORT).show()
        }

        //로그아웃
        binding.logout.setOnClickListener {
            //로그인 화면으로
            val intent = Intent(this, Login::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            auth?.signOut()
        }

//        databaseRef.addValueEventListener(object : ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                //원하는 함수 실행
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.d("상태","error : ${error.toException()}")
//            }
//
//        })
    }


    private fun firebasestorage(){
        val storage = Firebase.storage

        var storageRef = storage.reference

        var imagesRef: StorageReference ?= storageRef.child("")
    }
}