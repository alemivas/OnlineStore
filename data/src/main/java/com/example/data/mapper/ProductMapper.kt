package com.example.data.mapper

import com.example.data.local.entity.ProductDBO
import com.example.domain.models.Product

fun ProductDBO.toProduct() : Product {
    return Product(
        id = id,
        title = title,
        price = price,
        description = description,
        images = images,
        creationAt = creationAt,
        updatedAt = updatedAt,
        category = category
    )
}

fun Product.toProductDBO() : ProductDBO {
    return ProductDBO(
        id = id,
        title = title,
        price = price,
        description = description,
        images = images,
        creationAt = creationAt,
        updatedAt = updatedAt,
        category = category
    )
}