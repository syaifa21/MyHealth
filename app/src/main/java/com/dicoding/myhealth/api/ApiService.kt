package com.dicoding.myhealth.api


import com.dicoding.myhealth.api.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("signin")
    fun signin(
        @Field("username") name: String,
        @Field("password") password: String
    ): Call<LoginResponse>
}