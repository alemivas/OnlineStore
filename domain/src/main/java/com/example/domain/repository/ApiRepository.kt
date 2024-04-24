package com.example.domain.repository

import com.example.domain.models.Category
import com.example.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    suspend fun getCategories() : Flow<ApiResult<List<Category>>>
}