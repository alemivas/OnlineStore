package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.local.dao.ProductDao
import com.example.data.local.dao.UserDao
import com.example.data.local.entity.ProductDBO
import com.example.data.local.entity.StringListConverter
import com.example.data.local.entity.UserDBO

@Database(
    entities = [ProductDBO::class, UserDBO::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val productDao: ProductDao
    abstract val userDao: UserDao
}