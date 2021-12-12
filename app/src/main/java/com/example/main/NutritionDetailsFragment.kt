package com.example.main

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

class NutritionDetailsFragment: Fragment() {
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
        val nutritionFacts: List<NutritionFactsItem> = listOf(
                NutritionFactsItem("Énergie", "?", 293.toFloat()),
                NutritionFactsItem("Matières grasses / Lipides","?", 0.8.toFloat()),
                NutritionFactsItem("Acides gras saturés","?", 0.1.toFloat()),
                NutritionFactsItem("Glucides", "?", 8.4.toFloat()),
                NutritionFactsItem("Sucres", "?", 5.2.toFloat()),
                NutritionFactsItem("Fibres alimentaires", "?", 5.2.toFloat()),
                NutritionFactsItem("Protéines", "?", 4.8.toFloat()),
                NutritionFactsItem("Sel","?",0.75.toFloat()),
                NutritionFactsItem("Sodium","?", 0.295.toFloat()),
        )
        //TODO automatiser avec les règles : https://fr.openfoodfacts.org/reperes-nutritionnels

        DrawableCompat.setTintList(nutrient_level_lipid.background, ColorStateList.valueOf(nutrientLevelLow))
        DrawableCompat.setTintList(nutrient_level_saturated_fat.background, ColorStateList.valueOf(nutrientLevelLow))
        DrawableCompat.setTintList(nutrient_level_sugar.background, ColorStateList.valueOf(nutrientLevelModerate))
        DrawableCompat.setTintList(nutrient_level_salt.background, ColorStateList.valueOf(nutrientLevelHigh))

        super.onViewCreated(view, savedInstanceState)
    }
}