package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.UserDBO

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUser(user: UserDBO)

    @Query("SELECT * FROM userdbo WHERE isLogin = 1")
    suspend fun getIsLoginUser(): UserDBO?

    @Query("SELECT * FROM userdbo")
    suspend fun getAllUsers(): List<UserDBO>?
}