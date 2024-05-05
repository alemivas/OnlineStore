package com.example.domain.repository

import com.example.domain.models.Category
import com.example.domain.models.CategoryRequest
import com.example.domain.models.Product
import com.example.domain.models.ProductRequest
import com.example.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    suspend fun getCategories() : Flow<ApiResult<List<Category>>>
    suspend fun getProducts(
        limit: Int,
        offset: Int,
        title: String?,
        categoryId: Int?,
        priceMin: Int?,
        priceMax: Int?,
        ) : Flow<ApiResult<List<Product>>>

    suspend fun postProduct(request: ProductRequest)
    suspend fun updateProduct(id: Int, request: ProductRequest)
    suspend fun deleteProduct(id: Int)

    suspend fun postCategory(request: CategoryRequest)
    suspend fun updateCategory(id: Int, request: CategoryRequest)
    suspend fun deleteCategory(id: Int)
}