package com.dicoding.myhealth

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.myhealth.databinding.ActivityLoginBinding
import com.dicoding.myhealth.databinding.ActivityProfileBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity: AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        binding.logout.setOnClickListener{
            sharedPreferences.edit().clear().apply()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navbar)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.profile -> {
                    true
                }
                else -> false
            }
        }
    }
}