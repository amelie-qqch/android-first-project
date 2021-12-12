package com.example.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.test.R
import kotlinx.android.synthetic.main.nutritionnal_informations.*
import java.util.*
import kotlin.collections.HashMap

class NutritionalInformationsFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.nutritionnal_informations,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireContext()
        fillValues()
        super.onViewCreated(view, savedInstanceState)
    }

    fun fillValues(){
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
        //Quelle horreur ! : Faire un tableau des composant ?
        energy_per_hundred_grams.setText(nutritionFacts.get(0).quantityPerHundredGrams.toString())
        energy_per_share.setText(nutritionFacts.get(0).quantityPerPortion)
        fat_per_hundred_grams.setText(nutritionFacts.get(1).quantityPerHundredGrams.toString())
        fat_per_share.setText(nutritionFacts.get(1).quantityPerPortion)
        saturated_fat_per_hundred_grams.setText(nutritionFacts.get(2).quantityPerHundredGrams.toString())
        saturated_fat_per_share.setText(nutritionFacts.get(2).quantityPerPortion)
        saturated_fat_per_hundred_grams.setText(nutritionFacts.get(2).quantityPerHundredGrams.toString())
        saturated_fat_per_share.setText(nutritionFacts.get(2).quantityPerPortion)
        glucids_per_hundred_grams.setText(nutritionFacts.get(3).quantityPerHundredGrams.toString())
        glucids_per_share.setText(nutritionFacts.get(3).quantityPerPortion)
        sugar_per_hundred_grams.setText(nutritionFacts.get(4).quantityPerHundredGrams.toString())
        sugar_per_share.setText(nutritionFacts.get(4).quantityPerPortion)
        dietary_fibres_per_hundred_grams.setText(nutritionFacts.get(5).quantityPerHundredGrams.toString())
        dietary_fibres_per_share.setText(nutritionFacts.get(5).quantityPerPortion)
        proteins_per_hundred_grams.setText(nutritionFacts.get(6).quantityPerHundredGrams.toString())
        proteins_per_share.setText(nutritionFacts.get(6).quantityPerPortion)
        salt_per_hundred_grams.setText(nutritionFacts.get(7).quantityPerHundredGrams.toString())
        salt_per_share.setText(nutritionFacts.get(7).quantityPerPortion)
        sodium_per_hundred_grams.setText(nutritionFacts.get(8).quantityPerHundredGrams.toString())
        sodium_per_share.setText(nutritionFacts.get(8).quantityPerPortion)
    }
}