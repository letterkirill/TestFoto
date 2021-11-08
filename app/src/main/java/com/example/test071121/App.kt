package com.example.test071121

import android.app.Application
import com.example.test071121.di.ApplicationComponent
import com.example.test071121.di.DaggerApplicationComponent

class App: Application() {
    companion object {
        var instance: App? = null
            private set
    }

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this

        applicationComponent = DaggerApplicationComponent
            .builder()
            .build()
    }
}