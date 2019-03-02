package com.example.albumviewer.net

import com.example.albumviewer.common.ALBUM_ENDPOINT
import com.example.albumviewer.data.Album
import io.reactivex.Single
import retrofit2.http.GET

interface TypicodeService {

    @GET(ALBUM_ENDPOINT)
    fun getAlbums(): Single<List<Album>>
}