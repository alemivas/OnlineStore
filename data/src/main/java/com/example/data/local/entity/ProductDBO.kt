package com.example.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.Category


@Entity
data class ProductDBO(
    @PrimaryKey
    @ColumnInfo("id") val id: Int,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("price") val price: Int,
    @ColumnInfo("description") val description: String,
    @ColumnInfo("images") val images: List<String>,
    @ColumnInfo("creationAt") val creationAt: String,
    @ColumnInfo("updatedAt") val updatedAt: String,
    @ColumnInfo("category") val category: Category
)