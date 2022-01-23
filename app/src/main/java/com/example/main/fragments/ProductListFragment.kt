package com.example.main.fragments

import android.content.Intent
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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.main.model.NutritionFactsItem
import com.example.main.model.Product
import com.example.test.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_layout.*

class ProductListFragment: Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    private var products: List<Product> = listOf<Product>(
        Product(
            "Pains au lait",
            "Pasquier",
            " 3256540000698 ",
            "D",
            "https://fr.openfoodfacts.org/images/products/325/654/000/0698/front_fr.126.200.jpg",
            "350 g",
            "Belgique, France, Suisse, Royaume-Uni, États-Unis",
            "Farine de BLÉ 41%, Levain 20% (farine de BLÉ 11%, eau, sel), ŒUFS frais 11%, Sucre, BEURRE pâtissier, Huile de colza, Levure, LAIT écrémé en poudre (équivalent à 10% de LAIT écrémé reconstitué), Émulsifiant: mono et diglycérides d'acides gras, Sel, Protéines de BLÉ, GLUTEN de BLÉ, Extrait de carotte, Arômes, Antioxydant : acide ascorbique, Protéines de LAIT",
            "Œufs, Gluten, Lait",
            "E471 - Mono- et diglycérides d'acides gras alimentaires, E300 - Acide ascorbique",
            listOf(
                NutritionFactsItem("Énergie", "537 kj", 1535.toFloat()),
                NutritionFactsItem("Matières grasses / Lipides","4,55 g", 13.toFloat()),
                NutritionFactsItem("Acides gras saturés","1,61 g", 4.6.toFloat()),
                NutritionFactsItem("Glucides", "19,2 g", 55.toFloat()),
                NutritionFactsItem("Sucres", "3,85 g", 11.toFloat()),
                NutritionFactsItem("Fibres alimentaires", "0.49 g", 1.4.toFloat()),
                NutritionFactsItem("Protéines", "3,26 g", 9.3.toFloat()),
                NutritionFactsItem("Sel","0,385 g", 1.1.toFloat()),
                NutritionFactsItem("Sodium","?", 0.295.toFloat()),
            )
        ),
        Product(
            "Petits Pois sélection 'tendres et fondants'",
            "Cassegrain",
            "3083680085304",
            "A",
            "https://static.openfoodfacts.org/images/products/308/368/008/5304/front_fr.7.400.jpg",
            "400 g (280 g net égoutté)",
            "France, Japon, Suisse",
            "Petits pois 66%, eau, garniture 2,8% (salade, oignon grelot), sucre, sel, arôme naturel",
            "Aucune",
            "Aucun",
            listOf(
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
            )
    )

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

        val listAdapter = ListAdapter(products, object : ItemClickListener {
            override fun onItemClicked(position: Int) {
                Log.d("POSITION", products.get(position).name)
                findNavController()
                    .navigate(
                        ProductListFragmentDirections.actionItemListToItemDetails(
                            products.get(
                                position
                            )
                        )
                    )
            }
        })

        main_list.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = listAdapter
        }
        //Ouverture du scanner au click sur le bouton
//        products_start_scan.setOnClickListener {
//            startActivityForResult(
//                Intent("com.google.zxing.client.android.SCAN")
//                    .putExtra("SCAN_FORMATS", "EAN_13"),
//                100
//            )
//        }


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data == null || requestCode != 100){
            return
        }

        val barCodeType = data.getStringExtra("SCAN_RESULT_FORMAT")
        val barCode     = data.getStringExtra("SCAN_RESULT")
        // Use the data - in this case display it in a Toast.
        Toast.makeText(context, "Result: " + barCode, Toast.LENGTH_LONG).show()
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
