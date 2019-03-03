package com.example.albumviewer

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
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
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest : BaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val albumsRepository: DataSource = mock()

    private val observer: Observer<List<Album>> = mock()

    private lateinit var homeViewModel: HomeViewModel

    @Before
    override fun setup() {
        super.setup()
        homeViewModel = HomeViewModel(albumsRepository)
    }

    @Test
    fun testDataSuccessfullyRetrieved() {
        //Given
        val album = Album(0, 0, 1, "Test Album")
        val albums = listOf(album)
        whenever(albumsRepository.getSortedAlbums(Mockito.anyBoolean()))
            .thenReturn(Maybe.just(Collections.singletonList(album)))

        //When
        homeViewModel.getAlbumsObservable().observeForever(observer)
        homeViewModel.getData()

        //Then
        verify(observer).onChanged(albums)
    }

}