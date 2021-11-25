package com.example.test

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_layout)
        supportActionBar?.setBackgroundDrawable(
            ContextCompat.getDrawable(this, R.drawable.toolbar)
        )

        val toast = Toast.makeText(applicationContext, R.string.app_name, Toast.LENGTH_LONG)
        toast.show()

        val carrotAndPeas = Product(
            "https://i.imgur.com/JhYpzdR.jpeg",
            "Petits pois et carottes",
            "Cassegrain",
            "A",
            "234kCal",
            "400 g (280 g net égoutté)",
            arrayOf("France", "Japon", "Suisse"),
            arrayOf(
                "Petits pois 66%",
                "eau",
                "garniture 2,8%",
                "salade",
                "oignon",
                "grelot",
                "sucre",
                "sel",
                "arôme naturel"
            )
        )

        val chickenCurry = Product(
            "https://fr.openfoodfacts.org/images/products/324/883/294/0539/front_fr.82.200.jpg",
            "Poulet au Curry et Noix de Coco",
            "Weight Watchers",
            "A",
            "296kCal",
            "300 g",
            arrayOf(
                "Poulet origine UE",
                "Riz origine UE et Non UE"
            ),
            arrayOf(
                "riz cuit 22,5%",
                "eau",
                "poulet traité en salaison 20%",
                "carottes 11,3%",
                "poivrons rouges 6%",
                "oignons",
                "crème fraîche légère 3%",
                "noix de coco en poudre 2%",
                "champignons noirs réhydratés 1,5%",
                "raisins secs 0,9%"
            )
        )


        val listProduct: List<Product> = listOf(carrotAndPeas, chickenCurry)

        findViewById<RecyclerView>(R.id.main_list).run {
            adapter       = ListAdapter(listProduct)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

    }
    class ListAdapter(private val product: List<Product>) : RecyclerView.Adapter<ListItemCell>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemCell {
            return ListItemCell(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_layout, parent, false)
            )
        }

        override fun onBindViewHolder(holder: ListItemCell, position: Int) {
            Picasso.get().load(product[position].url).into(holder.url)
            holder.title.text       = product[position].title
            holder.brand.text       = product[position].brand
            holder.nutriScore.text  = "NutriScore : " + product[position].nutriscore
            holder.calories.text    = "Calories : " + product[position].calories
        }

        override fun getItemCount(): Int {
            return product.size
        }

    }

    class ListItemCell(v: View) : RecyclerView.ViewHolder(v) {

        val url         = v.findViewById<ImageView>(R.id.imageView)
        val title       = v.findViewById<TextView>(R.id.title)
        val brand       = v.findViewById<TextView>(R.id.brand)
        val nutriScore  = v.findViewById<TextView>(R.id.nutrition_score)
        val calories    = v.findViewById<TextView>(R.id.calory_score)

    }



    fun TextView.setBoldValue(value: String) {
        val index = value.indexOf(':')
        val spannable = SpannableString(value)

        spannable.setSpan(StyleSpan(Typeface.BOLD), 0, index, 0)
        setText(spannable)
    }
}