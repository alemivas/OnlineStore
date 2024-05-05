package com.example.domain.usecases.api_use_cases

import com.example.domain.models.CategoryRequest
import com.example.domain.repository.ApiRepository
import javax.inject.Inject

class UpdateCategoryUseCase  @Inject constructor(
    private val apiRepository: ApiRepository
) {

    suspend operator fun invoke(id: Int, request: CategoryRequest) = apiRepository.updateCategory(id, request)
}