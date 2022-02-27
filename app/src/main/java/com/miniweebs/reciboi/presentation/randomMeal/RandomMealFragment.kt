package com.miniweebs.reciboi.presentation.randomMeal

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
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
//            binding.ytLink.setOnClickListener {
//                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(yt_link)))
//            }
            val videoView = binding.ytVideo
            var done = false
            var yt_id = ""
            for(i in yt_link)
            {
                if(done) yt_id+=i
                if(i=='=') done=true;
            }
            videoView.settings.javaScriptEnabled=true
            videoView.settings.layoutAlgorithm=WebSettings.LayoutAlgorithm.SINGLE_COLUMN
            videoView.settings.pluginState=WebSettings.PluginState.ON
            videoView.loadData("<html><body style=\" margin: 0px; padding: 0px;\"><iframe class=\"youtube-player\" type=\"text/html\" src=\"https://www.youtube.com/embed/$yt_id\" frameborder=\"0\" style=\"width : 100%; height:100%; margin:0px; padding:0px; border:0;\" allowfullscreen></iframe></body></html>","text/html","utf-8");
            Glide.with(requireContext()).load(it.strMealThumb).into(binding.mealImage)
        }
        return view
    }
}