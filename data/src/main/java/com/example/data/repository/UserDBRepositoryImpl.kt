package com.example.data.repository

import com.example.data.localdata.UserDBO
import com.example.data.localdata.UsersDao
import com.example.data.localdata.toUser
import com.example.domain.models.User
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class UserDBRepositoryImpl @Inject constructor(
    private val usersDao: UsersDao
):UserRepository
{
    override suspend fun saveUser(user: User) {
        usersDao.insertUser(UserDBO(
            id = user.id,
            name = user.name,
            email = user.email,
            password = user.password,
            imageProfile = user.imageProfile,
            isManager = user.isManager,
            favoriteList = user.favoriteList,
            cartList = user.cartList,
            isLogin = user.isLogin
        ))
    }

    override suspend fun getIsLoginUser(): User? {
        return usersDao.getIsLoginUser()?.toUser()
    }

    override suspend fun getAllUser(): List<User>? {
        return usersDao.getAllUser()?.map { it.toUser() }
    }


}