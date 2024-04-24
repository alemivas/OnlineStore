package com.example.data.api

import com.example.domain.models.Category
import com.example.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface ApiService {

    fun getCategories(limit: Int = 10) : Flow<ApiResult<List<Category>>>
}