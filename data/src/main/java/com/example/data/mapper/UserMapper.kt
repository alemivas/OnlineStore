package com.example.data.mapper

import com.example.data.local.entity.UserDBO
import com.example.domain.models.User

fun UserDBO.toUser() = User(
    id = id,
    name = name,
    email = email,
    password = password,
    image = image,
    favoriteProductList = favoriteProductList,
    cartList = cartList,
    country = country,
    isManager = isManager,
    isLogin = isLogin,
)