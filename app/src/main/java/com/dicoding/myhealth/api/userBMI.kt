package com.dicoding.myhealth.api


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