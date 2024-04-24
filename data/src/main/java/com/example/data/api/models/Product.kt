package com.example.data.api.models

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val title: String,
    val price: Int,
    val description: String,
    val images: List<String>,
    val creationAt: String,
    val updatedAt: String,
    val category: Category
)

@Serializable
data class ProductCreateRequest(
    val title: String,
    val price: Int,
    val description: String,
    val categoryId: Int,
    val images: String
)
