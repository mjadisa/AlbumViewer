package com.example.albumviewer

import com.example.albumviewer.net.TypicodeService
import com.example.albumviewer.repo.DataSource
import com.example.albumviewer.repo.RemoteDataSource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoteDataSourceTest : BaseTest() {

    private val typicodeService: TypicodeService = mock()

    private lateinit var remoteDataSource: DataSource

    @Before
    override fun setup() {
        remoteDataSource = RemoteDataSource(typicodeService)
    }

    @Test
    fun testAscendingSort() {
        //Given
        whenever(typicodeService.getAlbums()).thenReturn(Single.just(getListOfAlbums()))
        //When
        val testObserver = remoteDataSource.getSortedAlbums(true).test()
        //Then
        val result = testObserver.values().get(0)
        Assert.assertTrue(result[0].title == "Amber")
        Assert.assertTrue(result[3].title.equals("Clay"))
    }

    @Test
    fun testDescendingSort() {
        //Given
        whenever(typicodeService.getAlbums()).thenReturn(Single.just(getListOfAlbums()))
        //When
        val testObserver = remoteDataSource.getSortedAlbums(false).test()
        //Then
        val result = testObserver.values().get(0)
        Assert.assertTrue(result[0].title == "Clay")
        Assert.assertTrue(result[3].title == "Amber")
    }


}