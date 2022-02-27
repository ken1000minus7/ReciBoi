package com.miniweebs.reciboi.data.api

class User {
    var name : String = ""
    var email : String = ""
    var image : String = ""
    var mealsList : MutableList<Meal> = mutableListOf()

    constructor() {}
    constructor(name : String, email : String, image : String)
    {
        this.name=name
        this.email=email
        this.image=image
    }

    constructor(name : String, email : String, image : String, mealsList : MutableList<Meal>)
    {
        this.name=name
        this.email=email
        this.image=image
        this.mealsList=mealsList
    }
}