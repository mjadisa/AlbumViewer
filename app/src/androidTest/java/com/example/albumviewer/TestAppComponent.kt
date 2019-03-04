package com.example.albumviewer

import android.app.Application
import com.example.albumviewer.di.AppComponent
import com.example.albumviewer.di.BuildersModule
import com.example.albumviewer.di.DatabaseModule
import com.example.albumviewer.di.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component(modules = [AndroidInjectionModule::class, BuildersModule::class, TestNetworkModule::class, DatabaseModule::class,
    RepositoryModule::class])@Singleton
interface TestAppComponent : AppComponent {
    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance fun application(application: Application): Builder
    }
}