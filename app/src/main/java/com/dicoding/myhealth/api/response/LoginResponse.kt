package com.dicoding.myhealth.api.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("roles")
	val roles: List<Any?>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
