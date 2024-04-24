package com.example.onlinestore.di

import com.example.data.repository.ApiServiceImpl
import com.example.domain.repository.ApiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindApiRepository(apiServiceImpl: ApiServiceImpl): ApiRepository
}