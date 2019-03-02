package com.example.albumviewer.repo

import com.example.albumviewer.data.Album
import io.reactivex.Maybe

interface DataSource {
    fun getSortedAlbums(isAscending: Boolean): Maybe<List<Album>>
    fun addAlbum(album: Album)
}