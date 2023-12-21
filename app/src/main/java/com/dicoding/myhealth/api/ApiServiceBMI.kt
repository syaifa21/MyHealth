package com.dicoding.myhealth.api


import com.dicoding.myhealth.api.response.BMIResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServiceBMI {
    @FormUrlEncoded
    @POST("hitung-kalori")
    fun signin(
        @Field("username") name: String,
        @Field("password") password: String
    ): Call<BMIResponse>

}