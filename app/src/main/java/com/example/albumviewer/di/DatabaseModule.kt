package com.example.albumviewer.di

import android.app.Application
import android.arch.persistence.room.Room
import com.example.albumviewer.common.DATABASE_NAME
import com.example.albumviewer.db.AlbumViewerDatabase
import com.example.albumviewer.repo.DataSource
import com.example.albumviewer.repo.LocalDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAlbumViewerDatabase(application: Application): AlbumViewerDatabase {
        return Room.databaseBuilder(application, AlbumViewerDatabase::class.java, DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    @Local
    fun provideLocalDataSource(albumViewerDatabase: AlbumViewerDatabase): DataSource =
        LocalDataSource(albumViewerDatabase)
}