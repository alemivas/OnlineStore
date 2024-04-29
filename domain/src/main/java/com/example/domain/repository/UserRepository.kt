package com.example.domain.repository

import com.example.domain.models.User

interface UserRepository {


    suspend fun saveUser(user: User)
    suspend fun getIsLoginUser(): User?
    suspend fun getAllUser(): List<User>?
}