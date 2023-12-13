package com.dicoding.myhealth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dicoding.myhealth.api.ApiConfig
import com.dicoding.myhealth.api.response.RegisterResponse
import com.dicoding.myhealth.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.registerBtn.setOnClickListener{
            register()
        }

    }

    private fun register() {

       if (binding.edEmailRegis.text?.isNotEmpty() == true){
           val client = ApiConfig.getApiService().signup(
               name = binding.edUsernameRegis.text.toString(),
               email = binding.edEmailRegis.text.toString(),
               password = binding.edPasswordRegis.text.toString(),

           )
           client.enqueue(object: Callback<RegisterResponse>{
               override fun onResponse(
                   call: Call<RegisterResponse>,
                   response: Response<RegisterResponse>
               ) {
                   val res = response.body()
                   if(response.isSuccessful && res != null){
                       finish()
                   }else{
                       Log.e("hasilogin", response.message())
                       Toast.makeText(this@RegisterActivity, response.message(), Toast.LENGTH_SHORT).show()
                   }
               }

               override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                   Toast.makeText(this@RegisterActivity, "Kesalahan server!", Toast.LENGTH_SHORT).show()
               }

           })
       }
    }
}