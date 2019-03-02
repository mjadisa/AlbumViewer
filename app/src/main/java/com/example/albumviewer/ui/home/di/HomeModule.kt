package com.example.albumviewer.ui.home.di

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.example.albumviewer.repo.AlbumsRepository
import com.example.albumviewer.ui.home.HomeActivity
import com.example.albumviewer.ui.home.HomeViewModel
import com.example.albumviewer.ui.home.HomeViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class HomeModule {
    @Provides
    @HomeScope
    fun provideHomeViewModelFactory(albumsRepository: AlbumsRepository) = HomeViewModelFactory(albumsRepository)

    @Provides
    @HomeScope
    fun provideHomeViewModel(homeActivity: HomeActivity, homeViewModelFactory: HomeViewModelFactory): HomeViewModel {
        return ViewModelProviders.of(homeActivity, homeViewModelFactory).get(HomeViewModel::class.java)
    }
}