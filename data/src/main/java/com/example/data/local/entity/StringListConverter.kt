package com.example.data.local.entity

import androidx.room.TypeConverter
import com.example.domain.models.Cart
import com.example.domain.models.Category
import com.example.domain.models.Product
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class StringListConverter {

    private val gson: Gson = GsonBuilder().setLenient().create()

    @TypeConverter
    fun fromTagList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun toTagList(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromProduct(value: String): Category {
        val category = object : TypeToken<Category>() {}.type
        return gson.fromJson(value, category)
    }

    @TypeConverter
    fun toProduct(category: Category): String {
        return gson.toJson(category)
    }

    @TypeConverter
    fun fromCartList(value: String): List<Cart> {
        val listType = object : TypeToken<List<Cart>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun toCartList(list: List<Cart>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromProductList(value: String): List<Product> {
        val listType = object : TypeToken<List<Product>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun toProductList(list: List<Product>): String {
        return gson.toJson(list)
    }
}