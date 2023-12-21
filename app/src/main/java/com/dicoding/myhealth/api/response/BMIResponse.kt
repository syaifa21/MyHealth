package com.dicoding.myhealth.api.response

import com.dicoding.myhealth.api.RekomendasiKaloriItem
import com.google.gson.annotations.SerializedName

data class BMIResponse(

	@field:SerializedName("rekomendasiKalori")
	val rekomendasiKalori: List<RekomendasiKaloriItem?>? = null,

	@field:SerializedName("kategoriBMI")
	val kategoriBMI: Int? = null,

	@field:SerializedName("bmi")
	val bmi: Any? = null
)

