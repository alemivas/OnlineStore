package com.example.domain.models


data class User(
    val id: Int,
    val name: String,
    val email:String,
    val password:String,
    val imageProfile:Int,
    val isManager:Boolean,
    val favoriteList:List<Product>,
    val cartList: List<Product>,//*
    val isLogin:Boolean
    )
