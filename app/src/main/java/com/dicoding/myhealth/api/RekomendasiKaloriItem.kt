package com.dicoding.myhealth.api

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    )


    companion object CREATOR : Parcelable.Creator<RekomendasiKaloriItem> {
        override fun createFromParcel(parcel: Parcel): RekomendasiKaloriItem {
            return RekomendasiKaloriItem(parcel)
        }

        override fun newArray(size: Int): Array<RekomendasiKaloriItem?> {
            return arrayOfNulls(size)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(grams)
        parcel.writeString(caloriesCategory)
        parcel.writeString(images)
        parcel.writeString(category)
        parcel.writeValue(fat)
        parcel.writeValue(id)
        parcel.writeValue(calories)
        parcel.writeValue(protein)
        parcel.writeValue(idCalories)
        parcel.writeString(food)
    }
}



