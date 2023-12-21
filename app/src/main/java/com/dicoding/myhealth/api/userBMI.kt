package com.dicoding.myhealth.api

import com.dicoding.myhealth.api.response.RekomendasiKaloriItem
import com.google.gson.annotations.SerializedName

data class userBMI(
    @SerializedName("berat") val berat : Int?,
    @SerializedName("tinggi") val tinggi : Int?,
    @SerializedName("umur") val umur : Int?,
    @SerializedName("gender") val gender : String?,
    @SerializedName("rekomendasiKalori")
    val rekomendasiKalori: List<RekomendasiKaloriItem?>? = null,

    @SerializedName("kategoriBMI")
    val kategoriBMI: Int? = null,

    @SerializedName("bmi")
    val bmi: Any? = null
)
data class RekomendasiKaloriItem(

    @SerializedName("Grams")
    val grams: Int? = null,

    @SerializedName("Calories_Category")
    val caloriesCategory: String? = null,

    @SerializedName("images")
    val images: String? = null,

    @SerializedName("Category")
    val category: String? = null,

    @SerializedName("Fat")
    val fat: Int? = null,

    @SerializedName("Id")
    val id: Int? = null,

    @SerializedName("Calories")
    val calories: Int? = null,

    @SerializedName("Protein")
    val protein: Int? = null,

    @SerializedName("Id_Calories")
    val idCalories: Int? = null,

    @SerializedName("Food")
    val food: String? = null
)