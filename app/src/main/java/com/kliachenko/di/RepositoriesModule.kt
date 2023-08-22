package com.kliachenko.di

import com.kliachenko.gitvoyager.data.database.UsersDatabase
import com.kliachenko.gitvoyager.data.network.ApiService
import com.kliachenko.gitvoyager.data.repository.UserRepositoryImpl
import com.kliachenko.gitvoyager.domain.repository.UsersRepository
import com.kliachenko.gitvoyager.domain.usecases.GetAllUsersUseCase
import com.kliachenko.gitvoyager.domain.usecases.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {

    @Provides
    @Singleton
    fun providesUserRepository(apiService: ApiService, userDb: UsersDatabase): UsersRepository {
        return UserRepositoryImpl(apiService, userDb)
    }

    @Provides
    @Singleton
    fun provideUserUses(repository: UsersRepository): UserUseCases {
        return UserUseCases(
            getAllUsersUseCase = GetAllUsersUseCase(repository)
        )
    }
}