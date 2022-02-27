package com.miniweebs.reciboi.presentation.meals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.miniweebs.reciboi.R
import com.miniweebs.reciboi.data.api.Category
import com.miniweebs.reciboi.data.api.MealByCategory
import com.miniweebs.reciboi.databinding.ActivityMealExtendedBinding
import com.miniweebs.reciboi.presentation.adapters.CategoryAdapter
import com.miniweebs.reciboi.presentation.adapters.MealAdapter
import com.miniweebs.reciboi.presentation.viewmodel.MealViewModel

class MealExtendedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMealExtendedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealExtendedBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val categoryBundle = intent.extras
        val category = categoryBundle?.getString("category")
        val switch = categoryBundle?.getInt("switch")
        Log.d("soe","$category")
        val gridLayoutManager = GridLayoutManager(this,2)
        binding.rvCategorySearchResult.layoutManager = gridLayoutManager
        val viewModel = ViewModelProvider(this)[MealViewModel::class.java]
        if (switch==1) {


            viewModel.getMealsByCategory(category!!)
            viewModel.mealsByCategory.observe(this) {
                val categories: MutableList<MealByCategory> = mutableListOf()
                categories.addAll(it.meals)
                binding.rvCategorySearchResult.adapter = CategoryAdapter(this, categories)
            }
        }
        else if (switch==2) {
            viewModel.getMealsByArea(category!!)
            viewModel.mealsByArea.observe(this) {
                val areas: MutableList<MealByCategory> = mutableListOf()
                areas.addAll(it.meals)
                binding.rvCategorySearchResult.adapter = CategoryAdapter(this, areas)
            }
        }
    }
}