package com.example.albumviewer.repo

import com.example.albumviewer.data.Album
import com.example.albumviewer.net.TypicodeService
import io.reactivex.Maybe

class RemoteDataSource(private val typicodeService: TypicodeService) : DataSource {

    override fun getSortedAlbums(isAscending: Boolean): Maybe<List<Album>> {
        return typicodeService.getAlbums()
            .map { it.sortedBy { album -> album.title } }
            .flatMapMaybe { Maybe.just(it) }
    }

    override fun addAlbum(album: Album) {
        //NO-OP
    }

}