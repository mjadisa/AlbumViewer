package com.example.albumviewer.repo

import com.example.albumviewer.data.Album
import com.example.albumviewer.db.AlbumViewerDatabase
import io.reactivex.Maybe

class LocalDataSource(private val albumViewerDatabase: AlbumViewerDatabase) : DataSource {
    override fun getSortedAlbums(isAscending: Boolean): Maybe<List<Album>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addAlbum(album: Album) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}