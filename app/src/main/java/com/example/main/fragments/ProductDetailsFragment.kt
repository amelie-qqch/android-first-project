package com.example.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.main.activity.MainActivity
import com.example.test.R
import kotlinx.android.synthetic.main.nav_details_layout.*
import kotlinx.android.synthetic.main.nutrition_details.*

class ProductDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(
            R.layout.nav_details_layout,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Nom de la page
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.details_page_name)
        ProductDetailsFragmentArgs.fromBundle(requireArguments()).product
//
//        //retour en arrière
//        findNavController().navigateUp()
        val navHost = childFragmentManager.findFragmentById(R.id.product_details_nav_host) as NavHostFragment
        NavigationUI.setupWithNavController(product_details_bottom_nav, navHost.navController)
    }
}