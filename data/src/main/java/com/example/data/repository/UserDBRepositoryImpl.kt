package com.example.data.repository

import com.example.data.local.dao.UserDao
import com.example.data.local.entity.UserDBO
import com.example.data.mapper.toUser
import com.example.domain.models.User
import com.example.domain.repository.UserDBRepository
import javax.inject.Inject

class UserDBRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserDBRepository {

    override suspend fun saveUser(user: User) {
        userDao.saveUser(
            UserDBO(
                id = user.id,
                name = user.name,
                email = user.email,
                password = user.password,
                favoriteProductList = user.favoriteProductList,
                cartList = user.cartList,
                isLogin = user.isLogin,
            )
        )
    }

    override suspend fun getUserById(id: String): User? {
        return userDao.getUserById(id)?.toUser()
    }

    override suspend fun getIsLoginUser(): User? {
        return userDao.getIsLoginUser()?.toUser()
    }

    override suspend fun getAllUsers(): List<User>? {
        return userDao.getAllUsers()?.map { it.toUser() }
    }
}