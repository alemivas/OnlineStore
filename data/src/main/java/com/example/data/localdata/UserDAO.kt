package com.example.data.localdata

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserDBO)

    @Query("SELECT * FROM userdbo WHERE isLogin = 1")
    suspend fun getIsLoginUser(): UserDBO?

    @Query("SELECT * FROM userdbo")
    suspend fun getAllUser(): List<UserDBO>?
}