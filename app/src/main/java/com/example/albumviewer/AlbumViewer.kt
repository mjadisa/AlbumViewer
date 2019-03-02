package com.example.albumviewer

import android.app.Application
import com.example.albumviewer.di.AppComponent
import com.example.albumviewer.di.DaggerAppComponent

class AlbumViewer : Application() {


    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()

    }
    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }
}