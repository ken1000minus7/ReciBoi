package com.miniweebs.reciboi.data.api

import retrofit2.Response
import retrofit2.http.GET

interface TheMealDBapi {
    @GET("random.php")
    suspend fun getSingleRandomMeal() : Response<MealList>
}