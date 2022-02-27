package com.miniweebs.reciboi.presentation.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.miniweebs.reciboi.data.api.Category
import com.miniweebs.reciboi.data.api.Country
import com.miniweebs.reciboi.data.api.Meal
import com.miniweebs.reciboi.databinding.FragmentSearchBinding
import com.miniweebs.reciboi.presentation.MainActivity
import com.miniweebs.reciboi.presentation.adapters.CountryAdapter
import com.miniweebs.reciboi.presentation.adapters.Listeners
import com.miniweebs.reciboi.presentation.adapters.ListenersCountry
import com.miniweebs.reciboi.presentation.adapters.MealAdapter
import com.miniweebs.reciboi.presentation.meals.MealExtendedActivity
import com.miniweebs.reciboi.presentation.viewmodel.MealViewModel

class SearchFragment : Fragment() , Listeners,ListenersCountry{
    private var _binding : FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MealViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater,container,false)
        val view = binding.root
        viewModel = ViewModelProvider(this)[MealViewModel::class.java]

        val gridLayoutManager = GridLayoutManager(requireContext(),2)
        val gridLayoutManagerForAreas = GridLayoutManager(requireContext(),2)
        binding.rvCategoriesDish.layoutManager = gridLayoutManager
        binding.rvAreasDishes.layoutManager = gridLayoutManagerForAreas

        viewModel.getCategories()
        viewModel.categories.observe(viewLifecycleOwner){
            val categories : MutableList<Category> = mutableListOf()
            categories.addAll(it.categories)
            binding.rvCategoriesDish.adapter = MealAdapter(requireContext(),categories,this)
        }

        viewModel.getCountries()
        viewModel.areas.observe(viewLifecycleOwner){
            val areas: MutableList<Country> = mutableListOf()
            areas.addAll(it.meals)
            binding.rvAreasDishes.adapter = CountryAdapter(requireContext(),areas,this)
        }
        return view
    }

    override fun onItemClicked(category: Category) {
        val intent = Intent(activity,MealExtendedActivity::class.java)
        intent.putExtra("category",category.strCategory)
        intent.putExtra("switch",1)
        Log.d("stringcar", category.strCategory)
        startActivity(intent)
    }

    override fun onItemClicked(category: Country) {
        val intent = Intent(activity,MealExtendedActivity::class.java)
        intent.putExtra("category",category.strArea)
        intent.putExtra("switch",2)
        Log.d("stringcar", category.strArea)
        startActivity(intent)
    }
}