package com.example.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.test.R
import kotlinx.android.synthetic.main.nav_details_layout.*

class ProductDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = (activity as AppCompatActivity)

        setHasOptionsMenu(true)
        //Affiche la flèche de retour arrière
        activity.supportActionBar?.setDisplayShowHomeEnabled(true)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //Nom de la page
        activity.supportActionBar?.title = getString(R.string.details_page_name)

        return inflater.inflate(
            R.layout.nav_details_layout,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ProductDetailsFragmentArgs.fromBundle(requireArguments()).product

        val navHost = childFragmentManager.findFragmentById(R.id.product_details_nav_host) as NavHostFragment
        NavigationUI.setupWithNavController(product_details_bottom_nav, navHost.navController)
    }

    @Override
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == android.R.id.home) {
//            activity?.onBackPressed() //fonctionne aussi
            findNavController().navigateUp()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}