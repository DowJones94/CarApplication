package com.example.carapplication

import android.app.Application

class App : Application(){

    val carTask = CarTask()

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}