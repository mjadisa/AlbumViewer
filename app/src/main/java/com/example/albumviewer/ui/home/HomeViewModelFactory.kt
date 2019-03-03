package com.example.albumviewer.ui.home

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.albumviewer.common.Utils
import com.example.albumviewer.repo.AlbumsRepository
import com.example.albumviewer.repo.DataSource
import java.lang.IllegalArgumentException

class HomeViewModelFactory(private val albumsRepository: DataSource,
                           private val utils: Utils) : ViewModelProvider.Factory {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(albumsRepository, utils) as T
        }
        throw IllegalArgumentException("Please make sure the parameter is of type HomeViewModel")
    }

}