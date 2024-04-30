package com.example.domain.usecases.product_db_use_cases

import com.example.domain.models.Product
import com.example.domain.repository.ProductDBRepository
import javax.inject.Inject

class GetAllProductsFromCacheUseCase @Inject constructor(
    private  val productDBRepository: ProductDBRepository
) {
    suspend operator fun invoke(): List<Product>? = productDBRepository.getAllProducts()
}