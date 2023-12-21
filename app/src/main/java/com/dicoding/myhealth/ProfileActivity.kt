package com.dicoding.myhealth

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

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
            showYesNoDialog()

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
    private fun showYesNoDialog() {
        val builder = AlertDialog.Builder(this)

        // Atur judul dan pesan dialog
        builder.setTitle("Confirmation")
            .setMessage("Are you sure you want to continue?")

        // Atur tombol positif (Yes)
        builder.setPositiveButton("Yes") { dialog, which ->
            // Aksi yang diambil jika pengguna memilih Yes
            // Misalnya, Anda dapat menambahkan kode untuk melanjutkan dengan operasi yang diinginkan
            // ...
            sharedPreferences.edit().clear().apply()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            // Tutup dialog setelah menyelesaikan aksi
            dialog.dismiss()
        }

        // Atur tombol negatif (No)
        builder.setNegativeButton("No") { dialog, which ->
            // Aksi yang diambil jika pengguna memilih No
            // Misalnya, Anda dapat menambahkan kode untuk menghentikan atau membatalkan operasi yang diinginkan
            // ...

            // Tutup dialog setelah menyelesaikan aksi
            dialog.dismiss()
        }

        // Tampilkan dialog
        val dialog = builder.create()
        dialog.show()
    }
}