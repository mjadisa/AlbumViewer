package com.example.albumviewer.db

import android.arch.persistence.room.*
import com.example.albumviewer.data.Album
import io.reactivex.Maybe

@Dao
interface AlbumsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbum(album: Album)

    @Query("SELECT * FROM albums ORDER BY title ASC")
    fun getAscendingAlbumsSortedByTitle(): Maybe<List<Album>>

    @Query("SELECT * FROM albums ORDER BY title DESC")
    fun getDescendingAlbumsSortedByTitle(): Maybe<List<Album>>
}