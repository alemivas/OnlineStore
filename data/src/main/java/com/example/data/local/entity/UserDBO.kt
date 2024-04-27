package com.example.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.Cart
import com.example.domain.models.Product

@Entity
data class UserDBO(
    @PrimaryKey
    @ColumnInfo("id") val id: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("email") val email: String,
    @ColumnInfo("password") val password: String,
    @ColumnInfo("favoriteProductList") val favoriteProductList: List<Product>,
    @ColumnInfo("cartList") val cartList: List<Cart>,
    @ColumnInfo("isLogin") val isLogin: Boolean,
)