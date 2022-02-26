package com.miniweebs.reciboi.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiInstance {
    private const val BASE_URL = "www.themealdb.com/api/json/v1/1/"
    val api : TheMealDBapi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheMealDBapi::class.java)
    }

}