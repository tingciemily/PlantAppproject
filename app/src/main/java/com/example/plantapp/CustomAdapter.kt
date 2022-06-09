package com.example.plantapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val mList: List<ItemViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    var onItemClick : ((ItemViewModel) -> Unit)? = null

    // 建立新的view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]
        holder.imageView.setImageResource(ItemsViewModel.image)
        holder.textView.text = ItemsViewModel.text
        holder.textView.setOnClickListener {
            onItemClick?.invoke(ItemsViewModel)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }
}
