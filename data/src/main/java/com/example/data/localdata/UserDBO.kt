package com.example.data.localdata

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.domain.models.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Entity
data class UserDBO(
    @PrimaryKey
    @ColumnInfo("id") val id: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("email") val email: String,
    @ColumnInfo("password") val password: String,
    @ColumnInfo("imageProfile") val imageProfile:Int,
    @ColumnInfo("isManager") val isManager:Boolean,
    @ColumnInfo("favoriteList") val favoriteList:List<Product>,
    @ColumnInfo("cartList") val cartList: List<Product>,//*
    @ColumnInfo("isLogin") val isLogin: Boolean
    )

class StringListConverter {

    //@TypeConverter
    //fun fromCardList(value: String): List<Product> {
    //    val listType = object : TypeToken<List<Product>>() {}.type
    //    return Gson().fromJson(value, listType)
    //}
//
    //@TypeConverter
    //fun toCardList(list: List<Product>): String {
    //    return Gson().toJson(list)
    //}

    @TypeConverter
    fun fromFavoriteList(value: String): List<Product> {
        val listType = object : TypeToken<List<Product>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toFavoriteLis(list: List<Product>): String {
        return Gson().toJson(list)
    }
}
