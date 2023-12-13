package com.dicoding.myhealth.api.response

import com.google.gson.annotations.SerializedName

class LoginResponse (

    @SerializedName("id")
    val id : Int? = null,

    @SerializedName("username")
    val username : String? = null,

    @SerializedName("email")
    val email : String? = null,

    @SerializedName("roles")
    val roles : String? = null
)


