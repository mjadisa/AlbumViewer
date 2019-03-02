package com.example.albumviewer.ui.home

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.albumviewer.repo.AlbumsRepository
import java.lang.IllegalArgumentException

class HomeViewModelFactory(private val albumsRepository: AlbumsRepository) : ViewModelProvider.Factory {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(albumsRepository) as T
        }
        throw IllegalArgumentException("Please make sure the parameter is of type HomeViewModel")
    }

}