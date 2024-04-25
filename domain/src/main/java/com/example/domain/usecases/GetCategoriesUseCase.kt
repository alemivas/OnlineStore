package com.example.domain.usecases

import com.example.domain.repository.ApiRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {

    suspend operator fun invoke() = apiRepository.getCategories()
}