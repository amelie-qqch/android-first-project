package com.example.main.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
        val name: String,
        val brand: String,
        val barCode: String,
        val nutriScore: String,
        val imageUrl: String,
        val weight: String,
        val sellingCountries: String,
        val ingredients: String,
        val allergens: String,
        val additives: String,
): Parcelable {

}
