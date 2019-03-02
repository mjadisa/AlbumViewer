package com.example.albumviewer.di

import android.app.Application
import com.example.albumviewer.AlbumViewer
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, DatabaseModule::class, RepositoryModule::class])
@Singleton
interface AppComponent {
    fun inject(albumViewer: AlbumViewer)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance fun application(application: Application): Builder
    }
}