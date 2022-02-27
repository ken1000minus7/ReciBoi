package com.miniweebs.reciboi.presentation.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miniweebs.reciboi.R
import com.miniweebs.reciboi.data.api.Meal
import com.miniweebs.reciboi.presentation.mealActivity.MealActivity

class UserMealAdapter(var context: Context, var mealList: MutableList<Meal>) :
    RecyclerView.Adapter<UserMealAdapter.MealViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.meal_list_item,parent,false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = mealList[position]
        holder.mealName.text=meal.strMeal
        holder.mealArea.text=meal.strArea
        holder.mealCategory.text=meal.strCategory
        Glide.with(context).load(meal.strMealThumb).into(holder.mealImage)
        holder.itemView.setOnClickListener {
            val intent = Intent(context,MealActivity::class.java)
            intent.putExtra("Meal",meal)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mealName = itemView.findViewById<TextView>(R.id.meal_item_name)
        val mealArea = itemView.findViewById<TextView>(R.id.meal_item_area)
        val mealCategory = itemView.findViewById<TextView>(R.id.meal_item_category)
        val mealImage = itemView.findViewById<ImageView>(R.id.meal_item_image)
    }
}