package com.example.data.repository

import com.example.data.api.ApiService
import com.example.domain.models.Category
import com.example.domain.models.CategoryRequest
import com.example.domain.models.Product
import com.example.domain.models.ProductRequest
import com.example.domain.repository.ApiRepository
import com.example.utils.ApiResult
import io.ktor.client.call.body
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(
    private val apiService: ApiService
) : ApiRepository {

    override suspend fun getCategories(): Flow<ApiResult<List<Category>>> = flow {
        emit(ApiResult.Loading())
        try {
            val categories = apiService.fetchCategories().body<List<Category>>()
            emit(ApiResult.Success(categories))
        } catch (e:Exception) {
            e.printStackTrace()
            emit(ApiResult.Error(e.message ?: "Something went wrong"))
        }
    }

    override suspend fun getProducts(
        limit: Int,
        offset: Int,
        title: String?,
        categoryId: Int?,
        priceMin: Int?,
        priceMax: Int?,
        ): Flow<ApiResult<List<Product>>> = flow {
        emit(ApiResult.Loading())
        try {
            val products = apiService.fetchProducts(
                limit,
                offset,
                title,
                categoryId,
                priceMin,
                priceMax
            ).body<List<Product>>()
            emit(ApiResult.Success(products))
        } catch (e:Exception) {
            e.printStackTrace()
            emit(ApiResult.Error(e.message ?: "Something went wrong"))
        }
    }

    override suspend fun postProduct(request: ProductRequest) {
        apiService.postProduct(request)
    }

    override suspend fun updateProduct(id: Int, request: ProductRequest) {
        apiService.updateProduct(id, request)
    }

    override suspend fun deleteProduct(id: Int) {
        apiService.deleteProduct(id)
    }

    override suspend fun postCategory(request: CategoryRequest) {
        apiService.postCategory(request)
    }

    override suspend fun updateCategory(id: Int, request: CategoryRequest) {
        apiService.updateCategory(id, request)
    }

    override suspend fun deleteCategory(id: Int) {
        apiService.deleteCategory(id)
    }
}