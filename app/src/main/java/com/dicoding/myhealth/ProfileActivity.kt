package com.dicoding.myhealth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.myhealth.databinding.ActivityLoginBinding
import com.dicoding.myhealth.databinding.ActivityProfileBinding

class ProfileActivity: AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}