package com.miniweebs.reciboi.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMealDBapi {
    @GET("random.php")
    suspend fun getSingleRandomMeal() : Response<MealList>

    @GET("categories.php")
    suspend fun getMealsByCategories() : Response<Categories>

    @GET("list.php?a=list")
    suspend fun getCountries() : Response<Areas>

    @GET("filter.php?c=")
    suspend fun getCategorisedMeal(@Query("c") category : String ) :Response<MealsByCategory>

    @GET("filter.php?a=")
    suspend fun getAreaWiseMeal(@Query("a") area: String) : Response<MealsByCategory>

    @GET("search.php")
    suspend fun getMealByName(@Query("s") name :String) : Response<MealList>

}