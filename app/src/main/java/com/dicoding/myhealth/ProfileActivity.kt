package com.dicoding.myhealth

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.myhealth.api.ApiConfigBMI
import com.dicoding.myhealth.api.RekomendasiKaloriItem
import com.dicoding.myhealth.api.userBMI
import com.dicoding.myhealth.databinding.ActivityProfileBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("DEPRECATION")
class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var sharedPreferences: SharedPreferences
    private val rekomendasiKaloriList: MutableList<RekomendasiKaloriItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        binding.logout.setOnClickListener {
            showYesNoDialog()
        }

        binding.submitBtn.setOnClickListener {
            binding.loading.visibility = View.VISIBLE
            submit()
        }

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navbar)
        bottomNavigationView.menu.findItem(R.id.profile).isChecked = true
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

    private fun showYesNoDialog() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Confirmation")
            .setMessage("Are you sure you want to continue?")

        builder.setPositiveButton("Yes") { dialog, which ->
            sharedPreferences.edit().clear().apply()
            startActivity(Intent(this@ProfileActivity, LoginActivity::class.java))
            finish()
            dialog.dismiss()
        }

        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun submit() {
        if (binding.edGender.text?.isNotEmpty() == true) {
            val client = ApiConfigBMI.getApiServiceBMI().addUser(
                userBMI(
                    berat = binding.edWeight.id,
                    tinggi = binding.edHeight.id,
                    umur = binding.edAge.id,
                    gender = binding.edGender.text.toString()
                )
            )
            client.enqueue(object : Callback<userBMI> {
                override fun onResponse(call: Call<userBMI>, response: Response<userBMI>) {
                    val res = response.body()
                    if (response.isSuccessful && res != null) {
                        // Response berhasil, dapatkan data rekomendasi kalori
                        val responseRekomendasiKaloriList = res.rekomendasiKalori


                        // Tambahkan data ke dalam list
                        responseRekomendasiKaloriList?.let {
                            val limitedList = it.take(10)
                            rekomendasiKaloriList.addAll(limitedList.filterNotNull())
                            saveRekomendasiKaloriListToSharedPreferences(rekomendasiKaloriList)
                            // Redirect to HomeActivity after saving data
                            startActivity(Intent(this@ProfileActivity, HomeActivity::class.java))
                            finish()
                        }
                    } else {
                        Log.e("hasilogin", response.message())
                        if (res != null) {
                            Toast.makeText(
                                this@ProfileActivity,
                                res.umur.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

                override fun onFailure(call: Call<userBMI>, t: Throwable) {
                    // Handle failure if needed
                }
            })
        }
    }

    // Metode untuk menyimpan data ke SharedPreferences
    private fun saveRekomendasiKaloriListToSharedPreferences(list: List<RekomendasiKaloriItem>) {
        val sharedPreferences = getSharedPreferences("rekomendasi", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val limitedList = list.take(10)
        val jsonList = convertListToJsonString(limitedList)
        editor.putString("rekomendasiKaloriList", jsonList)
        editor.apply()
    }

    // Metode untuk mengonversi list ke JSON string
// ...

    private fun convertListToJsonString(list: List<RekomendasiKaloriItem>): String {
        // Implement logic to convert list to JSON string using Gson
        val gson = Gson()
        val type = object : TypeToken<List<RekomendasiKaloriItem>>() {}.type
        return gson.toJson(list, type)
    }

}
