package com.miniweebs.reciboi.presentation.randomMeal

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.miniweebs.reciboi.databinding.FragmentRandomMealBinding
import com.miniweebs.reciboi.presentation.viewmodel.MealViewModel

class RandomMealFragment : Fragment() {
    private var _binding : FragmentRandomMealBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRandomMealBinding.inflate(inflater,container,false)
        val view = binding.root
        val viewModel = ViewModelProvider(this)[MealViewModel::class.java]
        viewModel.getRandomMeal()
        viewModel.randomMeal.observe(viewLifecycleOwner){
            binding.mealName.text = it.strMeal
            binding.mealArea.text = it.strArea
            binding.mealCategory.text = it.strCategory
            binding.tvInstructions.text = it.strInstructions
            val yt_link = it.strYoutube
            binding.ytLink.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(yt_link)))
            }
            Glide.with(requireContext()).load(it.strMealThumb).into(binding.mealImage)
        }
        return view
    }
}