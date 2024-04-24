package com.example.data.api.models

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Int,
    val name: String,
    val image: String,
    val creationAt: String,
    val updatedAt: String
)

@Serializable
data class CategoryCreateRequest(
    val name: String,
    val image: String
)