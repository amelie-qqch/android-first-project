package com.example.main

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
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.example.test.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setBackgroundDrawable(
            ContextCompat.getDrawable(this, R.drawable.toolbar)
        )
        //setContentView(R.layout.list_layout)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            //.replace(R.id.container, FirstFragment())
            .replace(R.id.container, ProductDetailsFragment())
            .commitAllowingStateLoss()

    }
    class ListAdapter(private val product: List<Product>, val listener: ItemClickListener) : RecyclerView.Adapter<ListItemCell>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemCell {
            return ListItemCell(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_layout, parent, false)
            )
        }

        override fun onBindViewHolder(holder: ListItemCell, position: Int) {
//            Picasso.get().load(product[position].imageUrl).into(holder.url)
//            holder.title.text       = product[position].title
//            holder.brand.text       = product[position].brand
//            holder.nutriScore.text  = "NutriScore : " + product[position].nutriscore
//            holder.calories.text    = "Calories : " + product[position].calories
//            //itemView vue désigne la cellule
//            holder.itemView.setOnClickListener{
//                listener.onItemClick(position)
//            }
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

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }
}