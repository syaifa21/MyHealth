package com.dicoding.myhealth

import RekomendasiAdapter
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dicoding.myhealth.api.RekomendasiKaloriItem
import com.dicoding.myhealth.databinding.ActivityHomeBinding
import com.dicoding.myhealth.dummydata.activity.Activity
import com.dicoding.myhealth.widget.SpaceItemDecoration
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Suppress("DEPRECATION")
class HomeActivity : AppCompatActivity() {
    private lateinit var rv_foods: RecyclerView
    private lateinit var rv_activity: RecyclerView
    private var Activitylist = ArrayList<Activity>()
    private lateinit var binding: ActivityHomeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var token: String
    private lateinit var rekomendasiKaloriList: List<RekomendasiKaloriItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rv_foods = findViewById(R.id.rv_foods)
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.corner_radius) // Gantilah R.dimen.spacing dengan resource yang sesuai
        val itemDecoration = SpaceItemDecoration(spacingInPixels)
        rv_foods.addItemDecoration(itemDecoration)
        if (intent.hasExtra("rekomendasiKaloriList")) {
            rekomendasiKaloriList =
                intent.getParcelableArrayListExtra("rekomendasiKaloriList")!!
            showRecyclerListRekomendasi()
        }
        rv_activity = findViewById(R.id.rv_activity)
        Activitylist.addAll(getListActivity())
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        token = sharedPreferences.getString("username", null).toString()

        Log.e("logintoken", token)
        if (sharedPreferences.getString("username", null) == null) {
            Log.d("Homeoperoper", "oper")
            startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
            finish()
        }
        val login = sharedPreferences.getString("username", null).toString()
        val tvusername = binding.username
        tvusername.text = login

        rekomendasiKaloriList = getDataFromSharedPreferences()
        Log.d("RekomendasiList", rekomendasiKaloriList.toString())
        showRecyclerListActivity()


        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navbar)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    true
                }

                R.id.profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    finish()
                    false
                }

                else -> false
            }
        }
    }

    private fun showRecyclerListRekomendasi() {
        val layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.HORIZONTAL
        )
        val rvRekomendasi = findViewById<RecyclerView>(R.id.rv_foods)
        rvRekomendasi.layoutManager = layoutManager
        val limitedList = rekomendasiKaloriList.take(10) // Take only 10 items
        val rekomendasiAdapter = RekomendasiAdapter(limitedList)
        rvRekomendasi.adapter = rekomendasiAdapter
    }


    private fun getListActivity(): ArrayList<Activity> {
        val activityname = resources.getStringArray(R.array.activity_name)
        val activitykalori = resources.getStringArray(R.array.activity_kalori)
        val image = resources.getStringArray(R.array.activity_img)
        val listactivity = ArrayList<Activity>()
        for (i in activityname.indices) {
            val activity = Activity(image[i], activityname[i], activitykalori[i])
            listactivity.add(activity)
        }
        return listactivity
    }

    private fun showRecyclerListActivity() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvFoods.layoutManager = layoutManager
        val rekomendasiAdapter = RekomendasiAdapter(rekomendasiKaloriList)
        binding.rvFoods.adapter = rekomendasiAdapter
    }

    private fun getDataFromSharedPreferences(): List<RekomendasiKaloriItem> {
        val sharedPreferences = getSharedPreferences("rekomendasi", MODE_PRIVATE)
        val jsonList = sharedPreferences.getString("rekomendasiKaloriList", null)
        val list = convertJsonStringToList(jsonList)
        return list.take(10)
    }

    private fun convertJsonStringToList(jsonString: String?): List<RekomendasiKaloriItem> {
        val gson = Gson()
        val type = object : TypeToken<List<RekomendasiKaloriItem>>() {}.type
        return gson.fromJson(jsonString, type) ?: emptyList()
    }
}