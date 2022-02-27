package com.miniweebs.reciboi.presentation.meals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.miniweebs.reciboi.data.api.Meal
import com.miniweebs.reciboi.data.api.MealByCategory
import com.miniweebs.reciboi.databinding.ActivityMealExtendedBinding
import com.miniweebs.reciboi.presentation.adapters.CategoryAdapter
import com.miniweebs.reciboi.presentation.adapters.ListenersCategory
import com.miniweebs.reciboi.presentation.mealActivity.MealActivity
import com.miniweebs.reciboi.presentation.viewmodel.MealViewModel

class MealExtendedActivity : AppCompatActivity() , ListenersCategory {
    private lateinit var binding: ActivityMealExtendedBinding
    private lateinit var viewModel : MealViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealExtendedBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this)[MealViewModel::class.java]
        val categoryBundle = intent.extras
        val category = categoryBundle?.getString("category")
        val switch = categoryBundle?.getInt("switch")
        Log.d("soe","$category")
        val gridLayoutManager = GridLayoutManager(this,2)
        binding.rvCategorySearchResult.layoutManager = gridLayoutManager
        if (switch==1) {


            viewModel.getMealsByCategory(category!!)
            viewModel.mealsByCategory.observe(this) {
                val categories: MutableList<MealByCategory> = mutableListOf()
                categories.addAll(it.meals)
                binding.rvCategorySearchResult.adapter = CategoryAdapter(this, categories,this)
            }
        }
        else if (switch==2) {
            viewModel.getMealsByArea(category!!)
            viewModel.mealsByArea.observe(this) {
                val areas: MutableList<MealByCategory> = mutableListOf()
                areas.addAll(it.meals)
                binding.rvCategorySearchResult.adapter = CategoryAdapter(this, areas,this)
            }
        }
    }

    override fun onItemClicked(category: MealByCategory) {
        viewModel.getMealById(category.idMeal)
        viewModel.mealById.observe(this){
            val meal : Meal = it
            Log.d("meas","$it")
            val intent = Intent(this, MealActivity::class.java)
            intent.putExtra("Meal",meal)
            startActivity(intent)
        }
    }


}