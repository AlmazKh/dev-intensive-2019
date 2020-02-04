package ru.skillbranch.devintensive

import android.app.Application
import android.content.Context

class App : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null

        fun applicationContext(): Context? = instance?.applicationContext
    }
}