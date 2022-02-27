package com.miniweebs.reciboi.presentation.randomMeal

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.miniweebs.reciboi.databinding.FragmentRandomMealBinding
import com.miniweebs.reciboi.presentation.viewmodel.MealViewModel
import java.util.*

class RandomMealFragment : Fragment() {
    private var _binding : FragmentRandomMealBinding? = null
    private lateinit var textToSpeech: TextToSpeech
    private var speaking : Boolean = false
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

            var ingredients = ""
            if(it.strIngredient1!="") ingredients+="•\t"+it.strIngredient1+"  " +(if(it.strMeasure1=="") "" else "("+it.strMeasure1+")")+"\n"
            if(it.strIngredient2!="") ingredients+="•\t"+it.strIngredient2+"  " +(if(it.strMeasure2=="") "" else "("+it.strMeasure2+")")+"\n"
            if(it.strIngredient3!="") ingredients+="•\t"+it.strIngredient3+"  " +(if(it.strMeasure3=="") "" else "("+it.strMeasure3+")")+"\n"
            if(it.strIngredient4!="") ingredients+="•\t"+it.strIngredient4+"  " +(if(it.strMeasure4=="") "" else "("+it.strMeasure4+")")+"\n"
            if(it.strIngredient5!="") ingredients+="•\t"+it.strIngredient5+"  " +(if(it.strMeasure5=="") "" else "("+it.strMeasure5+")")+"\n"
            if(it.strIngredient6!="") ingredients+="•\t"+it.strIngredient6+"  " +(if(it.strMeasure6=="") "" else "("+it.strMeasure6+")")+"\n"
            if(it.strIngredient7!="") ingredients+="•\t"+it.strIngredient7+"  " +(if(it.strMeasure7=="") "" else "("+it.strMeasure7+")")+"\n"
            if(it.strIngredient8!="") ingredients+="•\t"+it.strIngredient8+"  " +(if(it.strMeasure8=="") "" else "("+it.strMeasure8+")")+"\n"
            if(it.strIngredient9!="") ingredients+="•\t"+it.strIngredient9+"  " +(if(it.strMeasure9=="") "" else "("+it.strMeasure9+")")+"\n"
            if(it.strIngredient10!="") ingredients+="•\t"+it.strIngredient10+"  " +(if(it.strMeasure10=="") "" else "("+it.strMeasure10+")")+"\n"
            if(it.strIngredient11!="") ingredients+="•\t"+it.strIngredient11+"  " +(if(it.strMeasure11=="") "" else "("+it.strMeasure11+")")+"\n"
            if(it.strIngredient12!="") ingredients+="•\t"+it.strIngredient12+"  " +(if(it.strMeasure12=="") "" else "("+it.strMeasure12+")")+"\n"
            if(it.strIngredient13!="") ingredients+="•\t"+it.strIngredient13+"  " +(if(it.strMeasure13=="") "" else "("+it.strMeasure13+")")+"\n"
            if(it.strIngredient14!="") ingredients+="•\t"+it.strIngredient14+"  " +(if(it.strMeasure14=="") "" else "("+it.strMeasure14+")")+"\n"
            if(it.strIngredient15!="") ingredients+="•\t"+it.strIngredient15+"  " +(if(it.strMeasure15=="") "" else "("+it.strMeasure15+")")+"\n"
            if(it.strIngredient16!="") ingredients+="•\t"+it.strIngredient16+"  " +(if(it.strMeasure16=="") "" else "("+it.strMeasure16+")")+"\n"
            if(it.strIngredient17!="") ingredients+="•\t"+it.strIngredient17+"  " +(if(it.strMeasure17=="") "" else "("+it.strMeasure17+")")+"\n"
            if(it.strIngredient18!="") ingredients+="•\t"+it.strIngredient18+"  " +(if(it.strMeasure18=="") "" else "("+it.strMeasure18+")")+"\n"
            if(it.strIngredient19!="") ingredients+="•\t"+it.strIngredient19+"  " +(if(it.strMeasure19=="") "" else "("+it.strMeasure19+")")+"\n"
            if(it.strIngredient20!="") ingredients+="•\t"+it.strIngredient20+"  " +(if(it.strMeasure20=="") "" else "("+it.strMeasure20+")")+"\n"
            binding.tvIngredients.text=ingredients

            binding.tvInstructions.text = it.strInstructions

            val yt_link = it.strYoutube
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
            videoView.loadData("<html><body style=\" margin: 0px; padding: 0px;\"><iframe class=\"youtube-player\" type=\"text/html\" src=\"https://www.youtube.com/embed/$yt_id/?&theme=dark&autohide=2&modestbranding=1&showinfo=0\" frameborder=\"0\" style=\"width : 100%; height:100%; margin:0px; padding:0px; border:0;\" allowfullscreen></iframe></body></html>","text/html","utf-8");
            Glide.with(requireContext()).load(it.strMealThumb).into(binding.mealImage)
        }
        textToSpeech = TextToSpeech(context,TextToSpeech.OnInitListener {
            if(it!=TextToSpeech.ERROR)
            {
                textToSpeech.language= Locale.UK
            }
        })

        binding.tvIngredients.setOnClickListener {
            if(speaking)
            {
                textToSpeech.stop()
                speaking=false
            }
            else
            {
                textToSpeech.speak(
                    binding.tvIngredients.text.toString(),
                    TextToSpeech.QUEUE_FLUSH,
                    null
                )
                speaking=true
            }
        }

        binding.tvInstructions.setOnClickListener {
            if(speaking)
            {
                textToSpeech.stop()
                speaking=false
            }
            else
            {
                textToSpeech.speak(
                    binding.tvInstructions.text.toString(),
                    TextToSpeech.QUEUE_FLUSH,
                    null
                )
                speaking=true
            }
        }
        return view
    }
}