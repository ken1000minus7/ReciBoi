package com.miniweebs.reciboi.presentation.mealActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.webkit.WebSettings
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.miniweebs.reciboi.R
import com.miniweebs.reciboi.data.api.Meal
import com.miniweebs.reciboi.data.api.User
import com.miniweebs.reciboi.databinding.ActivityMealBinding
import java.util.*

class MealActivity : AppCompatActivity() {
    private var binding : ActivityMealBinding? = null
    private lateinit var textToSpeech: TextToSpeech
    private lateinit var auth : FirebaseAuth
    private lateinit var saveImage : ImageView
    private lateinit var meal : Meal
    private var saved = false
    private var speaking : Boolean = false
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        val view = binding!!.root
        setContentView(view)
        databaseReference = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()
        saveImage = binding!!.mealSaveImage
        val it : Meal= intent.getSerializableExtra("Meal") as Meal
        binding!!.mealName.text = it.strMeal
        binding!!.mealArea.text = it.strArea
        binding!!.mealCategory.text = it.strCategory
        meal = it

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
        binding!!.tvIngredients.text=ingredients

        binding!!.tvInstructions.text = it.strInstructions

        val yt_link = it.strYoutube
        val videoView = binding!!.ytVideo
        var done = false
        var yt_id = ""
        for(i in yt_link)
        {
            if(done) yt_id+=i
            if(i=='=') done=true;
        }
        videoView.settings.javaScriptEnabled=true
        videoView.settings.layoutAlgorithm= WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        videoView.settings.pluginState= WebSettings.PluginState.ON
        videoView.loadData("<html><body style=\" margin: 0px; padding: 0px;\"><iframe class=\"youtube-player\" type=\"text/html\" src=\"https://www.youtube.com/embed/$yt_id/?&theme=dark&autohide=2&modestbranding=1&showinfo=0\" frameborder=\"0\" style=\"width : 100%; height:100%; margin:0px; padding:0px; border:0;\" allowfullscreen></iframe></body></html>","text/html","utf-8");
        Glide.with(this).load(it.strMealThumb).into(binding!!.mealImage)

        textToSpeech = TextToSpeech(this,TextToSpeech.OnInitListener {
            if(it!=TextToSpeech.ERROR)
            {
                textToSpeech.language= Locale.UK
            }
        })

        binding!!.tvIngredients.setOnClickListener {
            if(speaking)
            {
                textToSpeech.stop()
                speaking=false
            }
            else
            {
                textToSpeech.speak(
                    binding!!.tvIngredients.text.toString(),
                    TextToSpeech.QUEUE_FLUSH,
                    null
                )
                speaking=true
            }
        }

        binding!!.tvInstructions.setOnClickListener {
            if(speaking)
            {
                textToSpeech.stop()
                speaking=false
            }
            else
            {
                textToSpeech.speak(
                    binding!!.tvInstructions.text.toString(),
                    TextToSpeech.QUEUE_FLUSH,
                    null
                )
                speaking=true
            }
        }

        binding!!.mealSaveImage.setOnClickListener {
            databaseReference.child("Users").child(auth.uid!!).get().addOnCompleteListener {
                if(it.isSuccessful)
                {
                    val user = it.result.getValue<User>()
                    var mealList = user?.mealsList
                    if(mealList==null) mealList= mutableListOf()
                    if(saved)
                    {
                        Log.d("check",mealList.toString())
                        for(i in mealList)
                        {
                            Log.d("check","sadgers loop mei")
                            if(i.idMeal==meal.idMeal)
                            {
                                saveImage.setImageResource(R.drawable.meal_not_saved)
                                saved = false
                                Log.d("check","sadgers")
                                mealList.remove(i)
                                Toast.makeText(this,"Meal removed", Toast.LENGTH_SHORT).show()
                                break
                            }
                        }
                    }
                    else
                    {
                        mealList.add(meal)
                        saveImage.setImageResource(R.drawable.meal_saved)
                        saved = true
                        Toast.makeText(this,"Meal saved", Toast.LENGTH_SHORT).show()
                    }
                    databaseReference.child("Users").child(auth.uid!!).child("mealsList").setValue(mealList)
                }
            }
        }


    }
    override fun onStart() {
        super.onStart()
        databaseReference.child("Users").child(auth.uid!!).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val user = p0.getValue(User::class.java)
                var mealList = user?.mealsList
                if(mealList==null) mealList= mutableListOf()
                for(i in mealList)
                {
                    if(i.idMeal==meal.idMeal)
                    {
                        saveImage.setImageResource(R.drawable.meal_saved)
                        saved = true
                        break
                    }
                }
                if(!saved) saveImage.setImageResource(R.drawable.meal_not_saved)
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}