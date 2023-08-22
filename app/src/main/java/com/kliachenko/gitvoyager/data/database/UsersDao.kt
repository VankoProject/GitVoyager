package com.kliachenko.gitvoyager.data.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kliachenko.gitvoyager.data.database.model.UserDbModel


@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsers(users: List<UserDbModel>?)

    @Query("SELECT * FROM users")
    fun pagingSource(): PagingSource<Int, UserDbModel>

    @Query("DELETE FROM users")
    suspend fun clearAllUsers()

}