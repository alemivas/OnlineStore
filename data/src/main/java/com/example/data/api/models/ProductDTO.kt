package com.example.data.api.models

import kotlinx.serialization.Serializable

@Serializable
data class ProductDTO(
    val id: Int,
    val title: String,
    val price: Int,
    val description: String,
    val images: List<String>,
    val creationAt: String,
    val updatedAt: String,
    val category: CategoryDTO
)

@Serializable
data class ProductCreateRequestDTO(
    val title: String,
    val price: Int,
    val description: String,
    val categoryId: Int,
    val images: String
)
