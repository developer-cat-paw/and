package com.example.help_me

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.help_me.databinding.ActivityProfileBinding

class Profile : AppCompatActivity() {
    private var mbinding: ActivityProfileBinding ?= null
    private val binding get() = mbinding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mbinding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}