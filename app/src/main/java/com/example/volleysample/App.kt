package com.example.volleysample

import android.app.Application
import android.content.Context

class App :  Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private lateinit var instance: App

        fun getInstance(): Context = instance.applicationContext
    }

}