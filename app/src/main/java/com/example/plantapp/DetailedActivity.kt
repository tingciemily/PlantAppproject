package com.example.plantapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        val plant = intent.getParcelableExtra<ItemViewModel>("plant")
        if (plant != null){
            val textView : TextView = this.findViewById(R.id.detailedActivityTv)
            val imageView : ImageView = findViewById(R.id.detailedActivityTv)
            textView.text = plant.text
            imageView.setImageResource(plant.image)
        }
    }
}