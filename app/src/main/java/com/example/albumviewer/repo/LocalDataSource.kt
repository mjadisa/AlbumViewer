package com.example.albumviewer.repo

import com.example.albumviewer.data.Album
import com.example.albumviewer.db.AlbumViewerDatabase
import io.reactivex.Maybe

class LocalDataSource(private val albumViewerDatabase: AlbumViewerDatabase) : DataSource {
    override fun getSortedAlbums(isAscending: Boolean): Maybe<List<Album>> {
        return if(isAscending) albumViewerDatabase.albumsDao().getAscendingAlbumsSortedByTitle()
        else albumViewerDatabase.albumsDao().getDescendingAlbumsSortedByTitle()
    }

    override fun addAlbum(album: Album) = albumViewerDatabase.albumsDao().insertAlbum(album)

}