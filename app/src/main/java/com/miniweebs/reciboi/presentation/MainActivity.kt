package com.miniweebs.reciboi.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.miniweebs.reciboi.R
import com.miniweebs.reciboi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)
        val bottomNavigationView = binding!!.bottomNavbar
        val navController = findNavController(R.id.main_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.randomMealFragment,R.id.searchFragment,R.id.userPrefFragment))
        setupActionBarWithNavController(navController,appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)
    }
}