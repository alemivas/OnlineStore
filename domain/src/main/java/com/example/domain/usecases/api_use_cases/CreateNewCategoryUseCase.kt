package com.example.domain.usecases.api_use_cases

import com.example.domain.models.CategoryRequest
import com.example.domain.repository.ApiRepository
import javax.inject.Inject

class CreateNewCategoryUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {

    suspend operator fun invoke(request: CategoryRequest) = apiRepository.postCategory(request)
}