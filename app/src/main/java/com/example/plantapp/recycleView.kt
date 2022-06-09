package com.example.plantapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plantapp.databinding.ActivityMainBinding

class recycleView : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_view)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerview.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ItemViewModel>()

        for (i in 1..10) {
            data.add(ItemViewModel(R.drawable.dandelion, "蒲公英"))
            data.add(ItemViewModel(R.drawable.wheat, "小麥"))
            data.add(ItemViewModel(R.drawable.lavender, "薰衣草"))
            data.add(ItemViewModel(R.drawable.green, "蕨類"))
            data.add(ItemViewModel(R.drawable.banyantree, "榕樹"))
            data.add(ItemViewModel(R.drawable.marguerite, "雛菊"))
            data.add(ItemViewModel(R.drawable.hibiscus, "水芙蓉"))
            data.add(ItemViewModel(R.drawable.maple, "楓葉"))
            data.add(ItemViewModel(R.drawable.pine, "松樹"))
            data.add(ItemViewModel(R.drawable.flower, "玫瑰"))
        }

        val adapter = CustomAdapter(data)
        recyclerview.adapter = adapter
            adapter.onItemClick = {
                val intent = Intent(this, DetailedActivity::class.java)
                startActivity(intent)
            }
    }
}
