package com.example.albumviewer

import com.example.albumviewer.di.AppComponent

class TestApplication : AlbumViewer() {



    private val appComponent: AppComponent by lazy {
        DaggerTestAppComponent.builder()
            .application(this)
            .build()
    }
    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

}