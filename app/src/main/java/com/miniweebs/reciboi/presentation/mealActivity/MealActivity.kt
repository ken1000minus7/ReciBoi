package com.miniweebs.reciboi.presentation.mealActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miniweebs.reciboi.R
import com.miniweebs.reciboi.databinding.ActivityMealBinding

class MealActivity : AppCompatActivity() {
    private var binding : ActivityMealBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)
    }
}