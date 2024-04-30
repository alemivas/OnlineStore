package com.example.domain.usecases.api_use_cases

import com.example.domain.repository.ApiRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {

    suspend operator fun invoke(
        limit: Int,
        offset: Int,
        title: String?,
        categoryId: Int?,
        priceMin: Int?,
        priceMax: Int?,
        ) = apiRepository.getProducts(limit, offset, title, categoryId, priceMin, priceMax)
}