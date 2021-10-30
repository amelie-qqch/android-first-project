package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val days = List(30, { day ->
            Calendar.getInstance().run {
                set(Calendar.DAY_OF_WEEK, day)
                get(Calendar.DAY_OF_WEEK).toInt()
            }
        })

        Calendar.MONDAY

        findViewById<RecyclerView>(R.id.main_list).run {
            adapter = ListAdapter(days)
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        }
    }
}

class ListAdapter(val days: List<Int>) : RecyclerView.Adapter<ListItemCell>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemCell {
        return ListItemCell(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListItemCell, position: Int) {
        holder.text1.text = when(days[position]){
            Calendar.MONDAY -> "Lundi"
            Calendar.TUESDAY -> "Mardi"
            Calendar.WEDNESDAY -> "Mercredi"
            Calendar.THURSDAY -> "Jeudi"
            Calendar.FRIDAY -> "Vendredi"
            Calendar.SATURDAY -> "Samedi"
            Calendar.SUNDAY -> "Dimanche"
            else -> "autre"
        };

    }

    override fun getItemCount(): Int {
        return days.size
    }

}

class ListItemCell(v: View) : RecyclerView.ViewHolder(v) {

    val text1 = v.findViewById<TextView>(R.id.line1)

}