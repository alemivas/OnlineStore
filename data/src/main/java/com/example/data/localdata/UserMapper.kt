package com.example.data.localdata

import com.example.domain.models.User

fun UserDBO.toUser() = User(
    id = id,
    name = name,
    email = email,
    password = password,
    imageProfile = imageProfile,
    isManager=isManager,
    favoriteList = favoriteList,
    cartList = cartList,
    isLogin = isLogin,
)