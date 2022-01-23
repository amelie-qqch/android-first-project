package com.example.main.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import com.example.test.R
import kotlinx.android.synthetic.main.nutrition_details.*

class ProductDetailsNutritionFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.nutrition_details,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val context = requireContext()

        val nutrientLevelLow        = ContextCompat.getColor(context, R.color.nutrient_level_low)
        val nutrientLevelModerate   = ContextCompat.getColor(context, R.color.nutrient_level_moderate)
        val nutrientLevelHigh       = ContextCompat.getColor(context, R.color.nutrient_level_high)


        //TODO automatiser avec les règles : https://fr.openfoodfacts.org/reperes-nutritionnels

        DrawableCompat.setTintList(nutrient_level_lipid.background, ColorStateList.valueOf(nutrientLevelLow))
        DrawableCompat.setTintList(nutrient_level_saturated_fat.background, ColorStateList.valueOf(nutrientLevelLow))
        DrawableCompat.setTintList(nutrient_level_sugar.background, ColorStateList.valueOf(nutrientLevelModerate))
        DrawableCompat.setTintList(nutrient_level_salt.background, ColorStateList.valueOf(nutrientLevelHigh))

        super.onViewCreated(view, savedInstanceState)
    }
}