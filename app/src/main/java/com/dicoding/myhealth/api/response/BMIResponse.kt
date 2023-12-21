package com.dicoding.myhealth.api.response

import com.google.gson.annotations.SerializedName

data class BMIResponse(

	@field:SerializedName("rekomendasiKalori")
	val rekomendasiKalori: List<RekomendasiKaloriItem?>? = null,

	@field:SerializedName("kategoriBMI")
	val kategoriBMI: Int? = null,

	@field:SerializedName("bmi")
	val bmi: Any? = null
)

data class RekomendasiKaloriItem(

	@field:SerializedName("Grams")
	val grams: Int? = null,

	@field:SerializedName("Calories_Category")
	val caloriesCategory: String? = null,

	@field:SerializedName("Category")
	val category: String? = null,

	@field:SerializedName("Fat")
	val fat: Int? = null,

	@field:SerializedName("Id")
	val id: Int? = null,

	@field:SerializedName("Calories")
	val calories: Int? = null,

	@field:SerializedName("Protein")
	val protein: Int? = null,

	@field:SerializedName("Id_Calories")
	val idCalories: Int? = null,

	@field:SerializedName("Food")
	val food: String? = null
)
