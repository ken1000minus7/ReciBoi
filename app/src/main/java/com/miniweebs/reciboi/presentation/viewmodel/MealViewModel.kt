package com.miniweebs.reciboi.presentation.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miniweebs.reciboi.App
import com.miniweebs.reciboi.data.api.ApiInstance
import com.miniweebs.reciboi.data.api.Meal
import kotlinx.coroutines.launch

class MealViewModel:ViewModel() {
    val randomMeal : MutableLiveData<Meal> = MutableLiveData()
    fun getRandomMeal(){
        viewModelScope.launch { 
            try {
                randomMeal.value = ApiInstance.api.getSingleRandomMeal().body()?.meals?.get(0)
            }catch (e:Exception){
                Toast.makeText(App.appContext, "Check Internet Access", Toast.LENGTH_SHORT).show()
                Log.d("error","$e")
                Log.d("this","${randomMeal.value}")
            }
        }
    }
}