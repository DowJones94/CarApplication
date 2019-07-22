package com.example.carapplication

import android.app.Application

class App : Application(){

    val fakeCarTask = FakeCarTask()

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}