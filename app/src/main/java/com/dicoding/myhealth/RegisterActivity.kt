package com.dicoding.myhealth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.myhealth.api.ApiConfig
import com.dicoding.myhealth.api.response.RegisterResponse
import com.dicoding.myhealth.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.registerBtn.setOnClickListener {
            binding.loading.visibility = View.VISIBLE
            register()
        }
        binding.loginTextview.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }

    }

    private fun register() {

        if (binding.edEmailRegis.text?.isNotEmpty() == true) {
            val client = ApiConfig.getApiService().signup(
                name = binding.edUsernameRegis.text.toString(),
                email = binding.edEmailRegis.text.toString(),
                password = binding.edPasswordRegis.text.toString(),

                )
            client.enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    val res = response.body()
                    if (response.isSuccessful && res != null) {
                        finish()
                    } else {
                        Log.e("hasilogin", response.message())
                        if (res != null) {
                            Toast.makeText(this@RegisterActivity, res.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, "Kesalahan server!", Toast.LENGTH_SHORT)
                        .show()
                }

            })
        }
    }
}