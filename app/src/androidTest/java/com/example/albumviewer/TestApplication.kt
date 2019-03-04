package com.example.albumviewer

import android.app.Activity
import com.example.albumviewer.di.AppComponent
import com.example.albumviewer.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

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