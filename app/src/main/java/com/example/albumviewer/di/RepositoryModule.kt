package com.example.albumviewer.di

import com.example.albumviewer.repo.AlbumsRepository
import com.example.albumviewer.repo.DataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    @Repository
    fun provideRepositoryModule(@Local localDataSource: DataSource,
                                @Remote remoteDataSource: DataSource): DataSource {
        return AlbumsRepository(localDataSource = localDataSource, remoteDataSource = remoteDataSource)
    }
}