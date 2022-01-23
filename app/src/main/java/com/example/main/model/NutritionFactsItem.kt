package com.example.main.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NutritionFactsItem (
        val unity: String,
        val quantityPerPortion: String,
        val quantityPerHundredGrams: Float,
): Parcelable{

}