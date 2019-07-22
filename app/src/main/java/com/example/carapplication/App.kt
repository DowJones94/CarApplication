package com.example.carapplication

import android.app.Application

class App : Application(){

    var carTask: CarTask? = null
    var carWorkPresenterImpl = CarWorkPresenterImpl()

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}