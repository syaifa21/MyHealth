package com.dicoding.myhealth.api


import com.dicoding.myhealth.api.response.BMIResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiServiceBMI {
    @Headers("Content-Type: application/json")
    @POST("hitung-bmi")
    fun addUser(@Body userData: userBMI): Call<userBMI>
}