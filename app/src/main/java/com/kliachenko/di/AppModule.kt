package com.kliachenko.di

import com.kliachenko.gitvoyager.data.network.ApiService
import com.kliachenko.gitvoyager.data.repository.UserRepositoryImpl
import com.kliachenko.gitvoyager.domain.repository.UsersRepository
import com.kliachenko.gitvoyager.domain.usecases.GetUsersUseCase
import com.kliachenko.gitvoyager.domain.usecases.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun baseUrl() = "https://api.github.com/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesUserRepository(apiService: ApiService): UsersRepository {
        return UserRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideUserUses(repository: UsersRepository): UserUseCases {
        return UserUseCases(
            getUsersUseCase = GetUsersUseCase(repository)
        )
    }
}