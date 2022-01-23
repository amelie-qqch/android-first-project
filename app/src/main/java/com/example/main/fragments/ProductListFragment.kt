package com.example.main.fragments

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.main.activity.MainActivity
import com.example.main.model.Product
import com.example.test.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.empty_list.*
import kotlinx.android.synthetic.main.list_layout.*

class ProductListFragment: Fragment() {

    private var layoutManager: RecyclerView.LayoutManager?              = null
    private var adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null
    private var products: List<Product> = emptyList()
    private var layout: Int                                             = R.layout.list_layout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        products = (activity as MainActivity).getProducts()

        if(products.isEmpty()){

            layout = R.layout.empty_list
        }

        return inflater.inflate(
            layout,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(products.isEmpty()){
            scan_product_button.setOnClickListener {
                //Ouverture du scanner au click sur le bouton
                startActivityForResult(
                    Intent("com.google.zxing.client.android.SCAN")
                        .putExtra("SCAN_FORMATS", "EAN_13"),
                    100
                )
            }

            return
        }


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
