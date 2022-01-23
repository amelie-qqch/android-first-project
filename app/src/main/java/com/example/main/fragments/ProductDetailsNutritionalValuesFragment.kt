package com.example.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.main.model.NutritionFactsItem
import com.example.test.R
import kotlinx.android.synthetic.main.nutritionnal_informations.*

class ProductDetailsNutritionalValuesFragment: Fragment() {
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
        super.onViewCreated(view, savedInstanceState)

        requireContext()
        fillValues()
    }

    fun fillValues(){
        val nutritionFacts = ProductDetailsFragmentArgs
            .fromBundle(
                requireParentFragment().requireParentFragment().requireArguments()
            ).product.nutritions

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