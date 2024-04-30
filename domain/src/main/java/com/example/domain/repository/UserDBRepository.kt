package com.example.domain.repository

import com.example.domain.models.User

interface UserDBRepository {

    suspend fun saveUser(user: User)
    suspend fun getIsLoginUser(): User?
    suspend fun getAllUsers(): List<User>?
}