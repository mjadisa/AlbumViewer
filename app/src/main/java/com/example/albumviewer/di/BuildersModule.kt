package com.example.albumviewer.di

import com.example.albumviewer.ui.home.HomeActivity
import com.example.albumviewer.ui.home.di.HomeModule
import com.example.albumviewer.ui.home.di.HomeScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {
    @ContributesAndroidInjector(modules = [HomeModule::class])
    @HomeScope
    abstract fun bindHomeActivity(): HomeActivity


}