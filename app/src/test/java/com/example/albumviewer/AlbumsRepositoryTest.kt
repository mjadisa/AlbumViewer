package com.example.albumviewer

import com.example.albumviewer.data.Album
import com.example.albumviewer.repo.AlbumsRepository
import com.example.albumviewer.repo.DataSource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.anyBoolean
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class AlbumsRepositoryTest : BaseTest() {
    private val localDataSource: DataSource = mock()

    private val remoteDataSource: DataSource = mock()

    private lateinit var albumsRepository: DataSource
    private val albums = mutableListOf<Album>()
    private val album = Album(0, 0, 1, "Test Album")

    @Before
    override fun setup() {
        super.setup()
        albumsRepository = AlbumsRepository(localDataSource, remoteDataSource)
        albums.add(album)
    }

    @Test
    fun testDataRetrievalFromApi() {
        //Given
        whenever(remoteDataSource.getSortedAlbums(anyBoolean())).thenReturn(Maybe.just(albums))
        whenever(localDataSource.getSortedAlbums(anyBoolean())).thenReturn(Maybe.empty())
        //When
        val testObserver = albumsRepository.getSortedAlbums(true).test()

        //Then
        verify(remoteDataSource).getSortedAlbums(true)
        verify(localDataSource).addAlbum(album)
        testObserver.assertValueCount(1)

    }

    @Test
    fun testDataRetrievalFromDb() {
        //Given
        whenever(remoteDataSource.getSortedAlbums(anyBoolean())).thenReturn(Maybe.error(IOException()))
        whenever(localDataSource.getSortedAlbums(anyBoolean())).thenReturn(Maybe.just(albums))
        //When
        val testObserver = albumsRepository.getSortedAlbums(true).test()

        //Then
        verify(remoteDataSource).getSortedAlbums(true)
        verify(localDataSource).getSortedAlbums(true)
        verify(localDataSource, times(0)).addAlbum(album)
        testObserver.assertValueCount(1)
    }
}