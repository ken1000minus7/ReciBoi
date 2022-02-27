package com.miniweebs.reciboi.presentation.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.miniweebs.reciboi.App
import com.miniweebs.reciboi.data.api.Category
import com.miniweebs.reciboi.data.api.Country
import com.miniweebs.reciboi.data.api.Meal
import com.miniweebs.reciboi.databinding.FragmentSearchBinding
import com.miniweebs.reciboi.presentation.MainActivity
import com.miniweebs.reciboi.presentation.adapters.*
import com.miniweebs.reciboi.presentation.mealActivity.MealActivity
import com.miniweebs.reciboi.presentation.meals.MealExtendedActivity
import com.miniweebs.reciboi.presentation.viewmodel.MealViewModel

class SearchFragment : Fragment(), Listeners,ListenersCountry,
    ListenersSearch,
    androidx.appcompat.widget.SearchView.OnQueryTextListener {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private var mealAdapter: MealAdapter? = null
    private lateinit var viewModel: MealViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModel = ViewModelProvider(this)[MealViewModel::class.java]
        setupSearchView()
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        val gridLayoutManagerForAreas = GridLayoutManager(requireContext(), 2)
        binding.rvCategoriesDish.layoutManager = gridLayoutManager
        binding.rvAreasDishes.layoutManager = gridLayoutManagerForAreas

        viewModel.getCategories()
        viewModel.categories.observe(viewLifecycleOwner) {
            val categories: MutableList<Category> = mutableListOf()
            categories.addAll(it.categories)
            binding.rvCategoriesDish.adapter = MealAdapter(requireContext(), categories, this)
        }

        viewModel.getCountries()
        viewModel.areas.observe(viewLifecycleOwner) {
            val areas: MutableList<Country> = mutableListOf()
            areas.addAll(it.meals)
            binding.rvAreasDishes.adapter = CountryAdapter(requireContext(), areas, this)
        }
        return view
    }

    private fun setupSearchView() {
        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        binding.searchView.isSubmitButtonEnabled = true
        binding.searchView.setIconifiedByDefault(false)
        binding.searchView.setOnQueryTextListener(this)
    }

    override fun onItemClicked(category: Category) {
        val intent = Intent(activity, MealExtendedActivity::class.java)
        intent.putExtra("category", category.strCategory)
        intent.putExtra("switch", 1)
        Log.d("stringcar", category.strCategory)
        startActivity(intent)
    }

    override fun onItemClicked(category: Country) {
        val intent = Intent(activity, MealExtendedActivity::class.java)
        intent.putExtra("category", category.strArea)
        intent.putExtra("switch", 2)
        Log.d("stringcar", category.strArea)
        startActivity(intent)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        if (App.appContext?.isOnline == false) {
            Toast.makeText(requireContext(), "Connect to Internet and Try Again", Toast.LENGTH_LONG)
                .show()
            return false
        }

        binding.rvAreasDishes.isVisible = false
        binding.tvCategories.text = "Searches based on $p0"
        binding.tvAreas.isVisible = false
        viewModel.getMealByName(p0!!)
        viewModel.mealByName.observe(viewLifecycleOwner) {
            val mealList: MutableList<Meal> = mutableListOf()
            mealList.addAll(it.meals)
            binding.rvCategoriesDish.adapter = SearchAdapter(requireContext(), mealList,this)
        }
        binding.searchView.clearFocus()
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return if (p0!!.isEmpty()) {
            binding.rvAreasDishes.isVisible = true
            binding.tvCategories.text = "Categories"
            binding.tvAreas.isVisible = true
            viewModel.getCategories()
            viewModel.categories.observe(viewLifecycleOwner) {
                val categories: MutableList<Category> = mutableListOf()
                categories.addAll(it.categories)
                binding.rvCategoriesDish.adapter = MealAdapter(requireContext(), categories, this)
            }
            true
        } else false
    }

    override fun onItemClicked(meal: Meal) {
        val intent = Intent(context,MealActivity::class.java)
        intent.putExtra("Meal",meal)
        startActivity(intent)
    }
}