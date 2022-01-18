package com.example.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.main.model.Product
import com.example.test.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.details_layout.*

class ProductDetailsSummaryFragment : Fragment() { // ne jamais passer d'argument dans le constructeur d'un Fragment
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

        val product = ProductDetailsFragmentArgs.fromBundle(requireParentFragment().requireParentFragment().requireArguments()).product

        product_name.setText(product.name)
        product_brand.setText(product.brand)
        product_bar_code.setText(product.barCode)
        product_weight.setText(product.weight)
        product_countries.setText(product.sellingCountries)
        product_ingredients.setText(product.ingredients)
        product_allergens.setText(product.allergens)
        product_additives.setText(product.additives)
        Picasso.get().load(product.imageUrl).into(product_picture)

        super.onViewCreated(view, savedInstanceState)
    }


}