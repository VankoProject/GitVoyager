package com.kliachenko.gitvoyager.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kliachenko.gitvoyager.data.database.model.UserDbModel


@Database(
    version = 1,
    entities = [UserDbModel::class]
)
abstract class UsersDatabase: RoomDatabase() {

      abstract val usersDao: UsersDao

}