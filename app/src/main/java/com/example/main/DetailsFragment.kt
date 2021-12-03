package com.example.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.test.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.details_layout.*

class DetailsFragment : Fragment() { // ne jamais passer d'argument dans le constructeur d'un Fragment
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.details_layout,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireContext()

        val carrotAndPeas = Product(
                "Petits pois et carottes",
                "Cassegrain",
                "3083680085304",
                "A",
                "https://static.openfoodfacts.org/images/products/308/368/008/5304/front_fr.7.400.jpg",
                "400 g (280 g net égoutté)",
                "France, Japon, Suisse",
                "Petits pois 66%, eau, garniture 2,8% (salade, oignon grelot), sucre, sel, arôme naturel",
                "Aucune",
                "Aucun"
        )

        product_name.setText(carrotAndPeas.name)
        product_brand.setText(carrotAndPeas.brand)
        product_bar_code.setText(carrotAndPeas.barCode)
        product_weight.setText(carrotAndPeas.weight)
        product_countries.setText(carrotAndPeas.sellingCountries)
        product_ingredients.setText(carrotAndPeas.ingredients)
        product_allergens.setText(carrotAndPeas.allergens)
        product_additives.setText(carrotAndPeas.additives)
        Picasso.get().load(carrotAndPeas.imageUrl).into(product_picture)

        super.onViewCreated(view, savedInstanceState)
    }


}