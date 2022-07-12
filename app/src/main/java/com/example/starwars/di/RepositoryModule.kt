package com.example.starwars.di

import com.example.starwars.data.repository.StarshipsRepositoryImpl
import com.example.starwars.domain.repository.StarshipsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindStarshipsRepository(starshipsRepositoryImpl: StarshipsRepositoryImpl): StarshipsRepository

}