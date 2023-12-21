package com.dicoding.myhealth.api


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiServiceBMI {
    @Headers("Content-Type: application/json")
    @POST("hitung-bmi")
    fun addUser(@Body userData: userBMI): Call<userBMI>
}