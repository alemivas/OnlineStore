package com.example.data.api

import com.example.data.api.models.Category
import com.example.data.api.models.CategoryCreateRequest
import com.example.data.api.models.Product
import com.example.data.api.models.ProductCreateRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Api(
    private val client: HttpClient
) {
    companion object Endpoints {
        private const val DOMAIN = "https://api.escuelajs.co"
        private const val API = "/api/v1"
        const val _PRODUCTS = "/products"
        const val PRODUCTS = "$DOMAIN$API/products"
        const val _CATEGORIES = "/categories"
        const val CATEGORIES = "$DOMAIN$API/categories"
    }

    suspend fun fetchProducts(
        limit: Int,
        offset: Int,
        title: String? = null,
        categoryId: Int? = null,
        priceMin: Int? = null,
        priceMax: Int? = null,
    ) = client.get(PRODUCTS) {
        parameter("limit", limit)
        parameter("offset", offset)
        title?.let { parameter("title", it) }
        categoryId?.let { parameter("categoryId", categoryId) }
        priceMin?.let { parameter("priceMin", it) }
        priceMax?.let { parameter("priceMax", it) }
    }.body<List<Product>>()

    suspend fun fetchProduct(
        id: Int
    ) = client.get(PRODUCTS) {
        url { appendPathSegments(id.toString()) }
    }.body<Product>()

    suspend fun postProduct(
        request: ProductCreateRequest
    ) = client.post(PRODUCTS) {
        contentType(ContentType.Application.Json)
        setBody(request)
    }.body<Product>()

    suspend fun fetchCategoryProducts(
        categoryId: Int,
        limit: Int,
        offset: Int
    ) = client.get(CATEGORIES) {
        url { appendPathSegments(categoryId.toString(), _PRODUCTS) }
        parameter("limit", limit)
        parameter("offset", offset)
    }.body<List<Product>>()

    suspend fun fetchCategories(
        limit: Int = 10
    ) = client.get(CATEGORIES) {
        parameter("limit", limit)
    }.body<List<Category>>()

    suspend fun fetchCategory(
        id: Int
    ) = client.get(CATEGORIES) {
        url { appendPathSegments(id.toString()) }
    }.body<Category>()

    suspend fun postCategory(
        request: CategoryCreateRequest
    ) = client.post(CATEGORIES) {
        contentType(ContentType.Application.Json)
        setBody(request)
    }.body<Category>()
}