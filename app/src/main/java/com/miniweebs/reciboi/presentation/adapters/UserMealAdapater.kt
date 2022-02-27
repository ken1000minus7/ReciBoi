package com.miniweebs.reciboi.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.miniweebs.reciboi.R
import com.miniweebs.reciboi.data.api.Meal

class UserMealAdapater : RecyclerView.Adapter<UserMealAdapater.MealViewHolder> {
    lateinit var context : Context
    var mealList : MutableList<Meal> = mutableListOf()
    constructor(context: Context, mealList: MutableList<Meal>)
    {
        this.context=context
        this.mealList=mealList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.meal_list_item,parent,false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = mealList.get(position)
        holder.mealName.text=meal.strMeal
        holder.mealArea.text=meal.strArea
        holder.mealCategory.text=meal.strCategory
        Glide.with(context).load(meal.strImageSource).into(holder.mealImage)
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