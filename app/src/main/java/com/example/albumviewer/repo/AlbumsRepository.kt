package com.example.albumviewer.repo

import com.example.albumviewer.data.Album
import io.reactivex.Maybe

class AlbumsRepository(private val localDataSource: DataSource,
                       private val remoteDataSource: DataSource) : DataSource {
    override fun getSortedAlbums(isAscending: Boolean): Maybe<List<Album>> {
        return remoteDataSource.getSortedAlbums(isAscending)
            .doOnSuccess { it.forEach { album -> localDataSource.addAlbum(album) } }
            .doOnError { it.printStackTrace() }
            .onErrorResumeNext(localDataSource.getSortedAlbums(isAscending))
    }

    override fun addAlbum(album: Album) {
        //No-OP
    }

}