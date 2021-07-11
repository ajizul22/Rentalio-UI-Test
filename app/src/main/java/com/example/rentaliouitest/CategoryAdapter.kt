package com.example.rentaliouitest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(private val onCategoryClickListener: OnCategoryClickListener) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    val listCategory = listOf(
            "City", "Suv", "MPV", "Jeep",
            "Classic", "Trail", "Box", "Bus",
            "Spec", "Custom\nRide", "Scooter", "Jet"
    )

    val image = listOf<Int>(
            R.drawable.city, R.drawable.suv, R.drawable.mpv, R.drawable.jeep,
            R.drawable.classic, R.drawable.trail, R.drawable.suv, R.drawable.city,
            R.drawable.mpv, R.drawable.custom_ride, R.drawable.scooter, R.drawable.jeep
    )

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var title = itemView.findViewById<TextView>(R.id.tv_category)
        var image = itemView.findViewById<ImageView>(R.id.iv_category)

    }

    override fun getItemCount(): Int = listCategory.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.title.text = listCategory[position]
        holder.image.setImageResource(image[position])


        holder.itemView.setOnClickListener {
            onCategoryClickListener.onCategoryClicked(position)
        }
    }

    interface OnCategoryClickListener{
        fun onCategoryClicked(position: Int)
    }


}