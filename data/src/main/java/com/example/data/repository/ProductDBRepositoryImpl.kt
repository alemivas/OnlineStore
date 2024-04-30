package com.example.data.repository

import com.example.data.local.dao.ProductDao
import com.example.data.mapper.toProduct
import com.example.data.mapper.toProductDBO
import com.example.domain.models.Product
import com.example.domain.repository.ProductDBRepository
import javax.inject.Inject

class ProductDBRepositoryImpl @Inject constructor(
    private val productDao: ProductDao
) : ProductDBRepository {

    override suspend fun insertProduct(products: List<Product>) {
        productDao.insertProductList(products.map { it.toProductDBO() })
    }

    override suspend fun getAllProducts(): List<Product>? {
        return productDao.getAllProducts()?.map { it.toProduct() }
    }
}