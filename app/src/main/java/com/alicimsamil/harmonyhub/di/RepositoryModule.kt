package com.alicimsamil.harmonyhub.di

import com.alicimsamil.harmonyhub.data.repository.TracksRepositoryImpl
import com.alicimsamil.harmonyhub.domain.repository.TracksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideTracksRepository(noteRepositoryImpl: TracksRepositoryImpl): TracksRepository
}