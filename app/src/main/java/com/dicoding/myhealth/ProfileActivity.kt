package com.dicoding.myhealth

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.myhealth.databinding.ActivityLoginBinding
import com.dicoding.myhealth.databinding.ActivityProfileBinding

class ProfileActivity: AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.logout.setOnClickListener{
            sharedPreferences.edit().clear().apply()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}