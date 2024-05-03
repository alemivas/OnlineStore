package com.example.data.repository

import com.example.data.local.dao.UserDao
import com.example.data.mapper.toUser
import com.example.data.mapper.toUserDBO
import com.example.domain.models.User
import com.example.domain.repository.UserDBRepository
import javax.inject.Inject

class UserDBRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserDBRepository {

    override suspend fun saveUser(user: User) {
        userDao.saveUser(user.toUserDBO())
    }

    override suspend fun getIsLoginUser(): User? {
        return userDao.getIsLoginUser()?.toUser()
    }

    override suspend fun getAllUsers(): List<User>? {
        return userDao.getAllUsers()?.map { it.toUser() }
    }
}