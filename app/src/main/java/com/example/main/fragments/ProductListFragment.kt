package com.example.main.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.main.model.Product
import com.example.test.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_layout.*

class ProductListFragment: Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.list_layout,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val milkRoll = Product(
            "Pains au lait",
            "Pasquier",
            " 3256540000698 ",
            "D",
            "https://fr.openfoodfacts.org/images/products/325/654/000/0698/front_fr.126.200.jpg",
            "350 g",
            "Belgique, France, Suisse, Royaume-Uni, États-Unis",
            "Farine de BLÉ 41%, Levain 20% (farine de BLÉ 11%, eau, sel), ŒUFS frais 11%, Sucre, BEURRE pâtissier, Huile de colza, Levure, LAIT écrémé en poudre (équivalent à 10% de LAIT écrémé reconstitué), Émulsifiant: mono et diglycérides d'acides gras, Sel, Protéines de BLÉ, GLUTEN de BLÉ, Extrait de carotte, Arômes, Antioxydant : acide ascorbique, Protéines de LAIT",
            "Œufs, Gluten, Lait",
            "E471 - Mono- et diglycérides d'acides gras alimentaires, E300 - Acide ascorbique"
        )

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

        val products = listOf(milkRoll, carrotAndPeas)
        val listAdapter = ListAdapter(products, object : ItemClickListener {
            override fun onItemClicked(position: Int) {
                Log.d("POSITION", products.get(position).name)
                findNavController()
                    .navigate(
                        ProductListFragmentDirections.actionItemListToItemDetails(products.get(position))
                    )
            }
        })

        main_list.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter  = listAdapter
        }

    }

    class ListAdapter(private val products: List<Product>, val listener: ItemClickListener) : RecyclerView.Adapter<ListItemCell>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemCell {
            return ListItemCell(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_layout, parent, false)
            )
        }

        //Affiche tel texte à telle position
        override fun onBindViewHolder(holder: ListItemCell, position: Int) {

            Picasso.get().load(products[position].imageUrl).into(holder.url)
            holder.title.text       = products[position].name
            holder.brand.text       = products[position].brand
            holder.nutriScore.text  = "NutriScore : " + products[position].nutriScore
//            holder.calories.text    = "Calories : " + products[position].

            //itemView vue désigne la cellule
            holder.itemView.setOnClickListener{
                listener.onItemClicked(position)
            }
        }

        //Indique le nombre de ligne à afficher dans la liste
        override fun getItemCount(): Int {
            return products.size
        }

    }

    class ListItemCell(view: View) : RecyclerView.ViewHolder(view) {
        val url         = view.findViewById<ImageView>(R.id.product_image_view)
        val title       = view.findViewById<TextView>(R.id.product_title_view)
        val brand       = view.findViewById<TextView>(R.id.product_brand_view)
        val nutriScore  = view.findViewById<TextView>(R.id.product_nutriscore_view)
        val calories    = view.findViewById<TextView>(R.id.product_calory_score_view)

    }

    fun TextView.setBoldValue(value: String) {
        val index = value.indexOf(':')
        val spannable = SpannableString(value)

        spannable.setSpan(StyleSpan(Typeface.BOLD), 0, index, 0)
        setText(spannable)
    }

    interface ItemClickListener {
        fun onItemClicked(position: Int)
    }

}
