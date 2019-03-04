package com.example.albumviewer

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.example.albumviewer.common.Utils
import com.example.albumviewer.data.Album
import com.example.albumviewer.repo.DataSource
import com.example.albumviewer.ui.home.HomeViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest : BaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val albumsRepository: DataSource = mock()

    private val dataObserver: Observer<List<Album>> = mock()

    private val errorObserver: Observer<String> = mock()

    private val utils: Utils = mock()

    private lateinit var homeViewModel: HomeViewModel

    @Before
    override fun setup() {
        super.setup()
        homeViewModel = HomeViewModel(albumsRepository, utils)
    }

    @Test
    fun testDataSuccessfullyRetrievedWhenOnline() {
        //Given
        val album = Album(0, 0, 1, "Test Album")
        val albums = listOf(album)
        whenever(albumsRepository.getSortedAlbums(Mockito.anyBoolean()))
            .thenReturn(Maybe.just(Collections.singletonList(album)))
        whenever(utils.isOnline()).thenReturn(true)

        //When
        homeViewModel.getAlbumsObservable().observeForever(dataObserver)
        homeViewModel.getData()

        //Then
        verify(dataObserver).onChanged(albums)
    }

    @Test
    fun testDataSuccessfullyRetrievedWhenOffline() {
        //Given
        val album = Album(0, 0, 1, "Test Album")
        val albums = listOf(album)
        whenever(albumsRepository.getSortedAlbums(Mockito.anyBoolean()))
            .thenReturn(Maybe.just(Collections.singletonList(album)))
        whenever(utils.isOnline()).thenReturn(false)

        //When
        homeViewModel.getAlbumsObservable().observeForever(dataObserver)
        homeViewModel.getErrorObservable().observeForever(errorObserver)

        homeViewModel.getData()

        //Then
        verify(dataObserver).onChanged(albums)
        verify(errorObserver).onChanged("Network Connection Not Available, cached values will be shown");
    }

    @Test
    fun testOfflineAndEmptyDatabase() {
        //Given
        whenever(albumsRepository.getSortedAlbums(true)).thenReturn(Maybe.empty())
        whenever(utils.isOnline()).thenReturn(false)

        //When
        homeViewModel.getErrorObservable().observeForever(errorObserver)
        homeViewModel.getData()

        //Then
        verify(errorObserver).onChanged("Network Connection and Cached Data Not Available, " +
                "Please try again when you have active network connection");
    }
}