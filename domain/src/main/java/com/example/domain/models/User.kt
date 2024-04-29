package com.example.domain.models

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val image: String,
    val favoriteProductList: List<Product>,
    val cartList: List<Cart>,
    val country: String,
    val isManager: Boolean,
    val isLogin: Boolean,
)