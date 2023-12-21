package com.dicoding.myhealth.api


import com.dicoding.myhealth.api.response.BMIResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiServiceBMI {
    @Multipart
    @POST("hitung-bmi")
    fun submituser(
        @Part("berat") berat: Int,
        @Part("tinggi") tinggi: Int,
        @Part("umur") umur: Int,
        @Part("gender") gender:String
    ): Call<BMIResponse>
}