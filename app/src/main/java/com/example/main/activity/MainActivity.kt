package com.example.main.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.main.model.NutritionFactsItem
import com.example.main.model.Product

import com.example.test.R
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var products: List<Product>                                 = listOf<Product>(
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rand = Random.nextInt(2)
        if(rand == 1) {
            this.products = emptyList()
        }

        supportActionBar?.setBackgroundDrawable(
            ContextCompat.getDrawable(this, R.drawable.toolbar)
        )
        supportActionBar?.setTitle(getString(R.string.products_page_name))

        //Masque la flèche de retour
        supportActionBar?.setDisplayShowHomeEnabled(false)

        //layout dans lequel se trouve le navigation graph
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.tab_scan -> {
                //Ouverture du scanner au click sur le bouton
                startActivityForResult(
                    Intent("com.google.zxing.client.android.SCAN")
                        .putExtra("SCAN_FORMATS", "EAN_13"),
                    100
                )
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data == null || requestCode != 100){
            return
        }

        val barCodeType = data.getStringExtra("SCAN_RESULT_FORMAT")
        val barCode     = data.getStringExtra("SCAN_RESULT")
        Toast.makeText(this, "Code barre: " + barCode, Toast.LENGTH_LONG).show()
    }

    fun getProducts(): List<Product>
    {
        return this.products
    }


}