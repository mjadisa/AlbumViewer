package com.example.albumviewer.ui.home.di

import com.example.albumviewer.ui.home.HomeActivity
import dagger.Binds
import dagger.Module

@Module
abstract class HomeActivityModule {
    @Binds
    @HomeScope
    abstract fun provideHomeActivity(homeActivity: HomeActivity): HomeActivity
}