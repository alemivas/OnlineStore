package com.example.domain.usecases.api_use_cases

import com.example.domain.models.ProductRequest
import com.example.domain.repository.ApiRepository
import javax.inject.Inject

class UpdateProductUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {

    suspend operator fun invoke(id: Int, request: ProductRequest) = apiRepository.updateProduct(id, request)
}