package com.kliachenko.di

import android.content.Context
import androidx.room.Room
import com.kliachenko.gitvoyager.data.database.UsersDao
import com.kliachenko.gitvoyager.data.database.UsersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideUsersDatabase(
        @ApplicationContext context: Context
    ): UsersDatabase =
        Room.databaseBuilder(
            context,
            UsersDatabase::class.java,
            DB_NAME
        ).build()

    @Provides
    fun provideUsersDao(database: UsersDatabase): UsersDao {
        return database.usersDao
    }

    private companion object {
        const val DB_NAME = "users.db"
    }


}