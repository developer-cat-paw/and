package com.example.help_me

import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.help_me.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
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
    private val database = Firebase.database("https://sexy-b53e8-default-rtdb.asia-southeast1.firebasedatabase.app/")

    private val data = database.getReference("data")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        var a: Uri?= null

        // 이미지뷰 클릭 시 엑티비티 전환
        binding.profile.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }

        storage = Firebase.storage

//        val storage = FirebaseStorage.getInstance("gs://sexy-b53e8.appspot.com")
//        val storageRef = storage.reference
//        storageRef.child("images/test.jpg").downloadUrl.addOnSuccessListener { uri -> //이미지 로드 성공시
//            Glide.with(applicationContext)
//                .load(uri)
//                .into(binding.)
//            Toast.makeText(this,"성공",Toast.LENGTH_SHORT).show()
//        }.addOnFailureListener { //이미지 로드 실패시
//            Toast.makeText(applicationContext, "실패", Toast.LENGTH_SHORT).show()
//        }

        data.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val test = snapshot.child("data")
                for(i in test.children){
                    Log.d("상태","성공 : ${i.children}")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                //읽어오기에 실패했을 때
                Log.d("상태","error : ${error.message}")
            }

        })

        val recyclerview_list = findViewById<RecyclerView>(R.id.recyclerHorizon)

        val cateList = ArrayList<ListItem>()


        cateList.add(ListItem("And"))
        cateList.add(ListItem("Web"))
        cateList.add(ListItem("ios"))
        cateList.add(ListItem("Sever"))
        cateList.add(ListItem("Ai"))
        cateList.add(ListItem("Design"))


        val listAdapter = ListAdapter(cateList)
        listAdapter.notifyDataSetChanged()

        recyclerview_list.adapter = listAdapter
        recyclerview_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val recyclerview_main = findViewById<RecyclerView>(R.id.recyclerview_main)

        val itemList = ArrayList<MainItem>()


        itemList.add(MainItem(a, "이경민", "으으으으으으으ㅡ으으으으으으으아누러뉴러ㅏㅠㅓㅣㅏㄴ류ㅓㅏㅠ너ㅠ러ㅏ뉴ㅓㅏ", "And"))
        itemList.add(MainItem(a, "김명준", "응아니야 봉구스봉구스봉구스봉구스봉구스밥버거 보연유혁녹.셔홍겨ㅏ", "Web"))
        itemList.add(MainItem(a, "최시훈", "으으으으으으으ㅡ으으으으으으으아누러뉴러ㅏㅠㅓㅣㅏㄴ류ㅓㅏㅠ너ㅠ러ㅏ뉴ㅓㅏ", "ios"))
        itemList.add(MainItem(a, "장범준", "으으으으으으으ㅡ으으으으으으으아누러뉴러ㅏㅠㅓㅣㅏㄴ류ㅓㅏㅠ너ㅠ러ㅏ뉴ㅓㅏ", "Sever"))
        itemList.add(MainItem(a, "황주완", "으으으으으으으ㅡ으으으으으으으아누러뉴러ㅏㅠㅓㅣㅏㄴ류ㅓㅏㅠ너ㅠ러ㅏ뉴ㅓㅏ", "Ai"))
        itemList.add(MainItem(a, "모성현", "으으으으으으으ㅡ으으으으으으으아누러뉴러ㅏㅠㅓㅣㅏㄴ류ㅓㅏㅠ너ㅠ러ㅏ뉴ㅓㅏ", "Design"))
        itemList.add(MainItem(a, "이경민", "으으으으으으으ㅡ으으으으으으으아누러뉴러ㅏㅠㅓㅣㅏㄴ류ㅓㅏㅠ너ㅠ러ㅏ뉴ㅓㅏ", "And"))
        itemList.add(MainItem(a, "이경민", "으으으으으으으ㅡ으으으으으으으아누러뉴러ㅏㅠㅓㅣㅏㄴ류ㅓㅏㅠ너ㅠ러ㅏ뉴ㅓㅏ", "And"))
        itemList.add(MainItem(a, "이경민", "으으으으으으으ㅡ으으으으으으으아누러뉴러ㅏㅠㅓㅣㅏㄴ류ㅓㅏㅠ너ㅠ러ㅏ뉴ㅓㅏ", "And"))



        val mainAdapter = MainRecyclerAdapter(itemList)
        mainAdapter.notifyDataSetChanged()

        recyclerview_main.adapter = mainAdapter
        recyclerview_main.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }
}