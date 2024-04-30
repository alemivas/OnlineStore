package com.example.domain.repository

import com.example.domain.models.Product

interface ProductDBRepository {

    suspend fun insertProduct(products: List<Product>)
    suspend fun getAllProducts(): List<Product>?
}