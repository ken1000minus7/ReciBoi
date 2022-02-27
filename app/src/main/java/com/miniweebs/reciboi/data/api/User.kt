package com.miniweebs.reciboi.data.api

class User {
    lateinit var name : String
    lateinit var email : String
    lateinit var image : String
    var mealsList : List<Meal> = mutableListOf()
    constructor(name : String, email : String, image : String)
    {
        this.name=name
        this.email=email
        this.image=image
    }

    constructor(name : String, email : String, image : String, mealsList : List<Meal>)
    {
        this.name=name
        this.email=email
        this.image=image
        this.mealsList=mealsList
    }
}