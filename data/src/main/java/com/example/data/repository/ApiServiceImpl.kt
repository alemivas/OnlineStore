package com.example.data.repository

import com.example.data.api.aaaa.Api.Endpoints.CATEGORIES
import com.example.domain.models.Category
import com.example.domain.repository.ApiRepository
import com.example.utils.ApiResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(
    private val httpClient: HttpClient
) : ApiRepository {

    override suspend fun getCategories(): Flow<ApiResult<List<Category>>> = flow{
        emit(ApiResult.Loading())
        try {
            emit(ApiResult.Success(httpClient.get(CATEGORIES) {
                parameter("limit",10)
            }.body<List<Category>>()))
        }catch (e:Exception){
            e.printStackTrace()
            emit(ApiResult.Error(e.message ?: "Something went wrong"))
        }
    }
}