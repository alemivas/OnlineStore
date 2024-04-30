package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.ProductDBO

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductList(products: List<ProductDBO>)

    @Query("SELECT * FROM productdbo")
    suspend fun getAllProducts(): List<ProductDBO>?
}