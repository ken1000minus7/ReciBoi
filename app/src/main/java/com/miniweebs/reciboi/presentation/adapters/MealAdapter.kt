package com.miniweebs.reciboi.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miniweebs.reciboi.R
import com.miniweebs.reciboi.data.api.Category

class MealAdapter(private val context: Context, private val listOfMeals:MutableList<Category>, private val listener: Listeners) : RecyclerView.Adapter<MealAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mealText: TextView = itemView.findViewById(R.id.tv_name)
        val mealImage: ImageView = itemView.findViewById(R.id.img_category)
        private val mealCard: CardView = itemView.findViewById(R.id.card_view_meal)

        init {
            mealCard.setOnClickListener {
                listener.onItemClicked(listOfMeals[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.card_meal,parent,false)
return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entity = listOfMeals[position]
        holder.mealText.text = entity.strCategory
        Glide.with(context).load(entity.strCategoryThumb).into(holder.mealImage)
    }

    override fun getItemCount(): Int =
        listOfMeals.size
}
interface Listeners{
    fun onItemClicked(category: Category)
}
