package com.example.main.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat

import com.example.test.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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


}