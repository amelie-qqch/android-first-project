package com.example.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.new_details_layout.view.*

class ProductDetailsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.new_details_layout,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.product_details_bottom_nav.setOnNavigationItemSelectedListener {
            childFragmentManager.beginTransaction()
                .replace(
                    R.id.product_details_nav_host, when (it.itemId) {
                        R.id.tab_fiche      -> FirstFragment()
                        R.id.tab_tableau    -> Fragment2()
                        R.id.tab_nutrition  -> Fragment3()
                        else -> throw Exception("Je panique")
                    }
                )
                .commitAllowingStateLoss()

            true

        }

    }
}