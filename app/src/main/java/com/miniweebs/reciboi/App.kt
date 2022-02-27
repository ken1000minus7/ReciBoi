package com.miniweebs.reciboi

import android.app.Application

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
    companion object{
        var appContext :App? = null
    }
}