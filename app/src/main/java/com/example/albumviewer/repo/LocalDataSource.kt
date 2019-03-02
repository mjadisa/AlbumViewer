package com.example.albumviewer.repo

import com.example.albumviewer.data.Album
import io.reactivex.Maybe

class LocalDataSource : DataSource {
    override fun getSortedAlbums(isAscending: Boolean): Maybe<List<Album>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addAlbum(album: Album) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}