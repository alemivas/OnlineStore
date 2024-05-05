package com.example.domain.usecases.api_use_cases

import com.example.domain.repository.ApiRepository
import javax.inject.Inject

class DeleteCategoryUseCase  @Inject constructor(
    private val apiRepository: ApiRepository
) {

    suspend operator fun invoke(id: Int) = apiRepository.deleteCategory(id)
}