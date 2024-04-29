package com.example.onlinestore.di

import com.example.data.repository.ApiServiceImpl
import com.example.data.repository.UserDBRepositoryImpl
import com.example.domain.repository.ApiRepository
import com.example.domain.repository.UserRepository
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
    fun bindUserRepository(userDBRepositoryImpl: UserDBRepositoryImpl):UserRepository



    @Binds
    @Singleton
    fun bindApiRepository(apiServiceImpl: ApiServiceImpl): ApiRepository
}

