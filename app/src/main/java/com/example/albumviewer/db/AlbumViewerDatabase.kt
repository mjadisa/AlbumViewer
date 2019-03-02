package com.example.albumviewer.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.albumviewer.common.DATABASE_VERSION
import com.example.albumviewer.data.Album

@Database(entities = [Album::class], version = DATABASE_VERSION)
abstract class AlbumViewerDatabase : RoomDatabase() {
    abstract fun albumsDao(): AlbumsDao
}