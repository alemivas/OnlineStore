package com.example.data.mapper

import com.example.data.local.entity.UserDBO
import com.example.domain.models.User

fun UserDBO.toUser() = User(
    id = id,
    name = name,
    email = email,
    password = password,
    favoriteProductList = favoriteProductList,
    cartList = cartList,
    isLogin = isLogin,
)