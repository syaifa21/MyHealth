package com.dicoding.myhealth.api


import com.dicoding.myhealth.api.response.LoginResponse
import com.dicoding.myhealth.api.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServiceBMI {
    @FormUrlEncoded
    @POST("signin")
    fun signin(
        @Field("username") name: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("signup")
    fun signup(
        @Field("username") name: String,
        @Field("email") email: String,
        @Field("password") password: String,

        ): Call<RegisterResponse>
}