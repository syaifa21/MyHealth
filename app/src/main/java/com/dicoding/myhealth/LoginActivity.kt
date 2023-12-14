package com.dicoding.myhealth

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.dicoding.myhealth.api.ApiConfig
import com.dicoding.myhealth.api.response.LoginResponse
import com.dicoding.myhealth.databinding.ActivityLoginBinding

import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {


    lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        Log.d("Sharedtest", "ini $sharedPreferences")
        binding.loginBtn.setOnClickListener {
            if (binding.username.text?.isNotEmpty() == true && binding.edLoginPassword.text?.isNotEmpty() == true) {
                lifecycleScope.launch {
                    val client = ApiConfig.getApiService().signin(
                        name = binding.username.text.toString(),
                        password = binding.edLoginPassword.text.toString()
                    )

                    client.enqueue(object : Callback<LoginResponse> {
                        override fun onResponse(
                            call: Call<LoginResponse>,
                            response: Response<LoginResponse>
                        ) {
                            val res = response.body()
                            if (res !== null) {
                                Log.d("hasillogin", "berhasil")
                                sharedPreferences
                                    .edit()
                                    .putString("token", res.username)
                                    .putString("token", res.email)
                                    .putInt("token", res.id!!)
                                    .apply()

                                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                                finish()
                            } else {
                                Toast.makeText(
                                    this@LoginActivity,
                                    response.message(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            TODO("Not yet implemented")
                        }

                    })
                }
            }
        }
    }
}