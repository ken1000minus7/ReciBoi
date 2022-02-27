package com.miniweebs.reciboi.presentation.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.common.api.Api
import com.miniweebs.reciboi.App
import com.miniweebs.reciboi.data.api.*
import kotlinx.coroutines.launch

class MealViewModel:ViewModel() {
    val randomMeal : MutableLiveData<Meal> = MutableLiveData()
    val categories : MutableLiveData<Categories> = MutableLiveData()
    val areas : MutableLiveData<Areas> = MutableLiveData()
    val mealsByCategory : MutableLiveData<MealsByCategory> = MutableLiveData()
    val mealsByArea : MutableLiveData<MealsByCategory> = MutableLiveData()
    val mealByName : MutableLiveData<MealList> = MutableLiveData()
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
    fun getCategories(){
        viewModelScope.launch {
            try {
                categories.value = ApiInstance.api.getMealsByCategories().body()
            }catch (e:Exception){
                Toast.makeText(App.appContext, "Check Internet Access", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun getCountries(){
        viewModelScope.launch {
            try {
                areas.value = ApiInstance.api.getCountries().body()
            }catch (e:Exception){
                Toast.makeText(App.appContext, "Check Internet Access", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getMealsByCategory(category:String){
        viewModelScope.launch {
            try {
                mealsByCategory.value = ApiInstance.api.getCategorisedMeal(category).body()
            }catch (e:Exception){
                Toast.makeText(App.appContext, "Check Internet Access", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun getMealsByArea(category:String){
        viewModelScope.launch {
            try {
                mealsByArea.value = ApiInstance.api.getAreaWiseMeal(category).body()
            }catch (e:Exception){
                Toast.makeText(App.appContext, "Check Internet Access", Toast.LENGTH_SHORT).show()
                Log.d("some","$e")
            }
        }
    }

    fun getMealByName(name: String){
        viewModelScope.launch {
            try {
                mealByName.value = ApiInstance.api.getMealByName(name).body()
                Log.d("somesome","${mealByName.value}")
            }catch (e:Exception){
                Toast.makeText(App.appContext, "Check Internet Access", Toast.LENGTH_SHORT).show()
            }
        }
    }

}