package com.kliachenko.gitvoyager.data.repository

import com.kliachenko.gitvoyager.data.mapper.toUser
import com.kliachenko.gitvoyager.data.network.ApiService
import com.kliachenko.gitvoyager.domain.model.User
import com.kliachenko.gitvoyager.domain.repository.UsersRepository
import com.kliachenko.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepositoryImpl(
    private val apiService: ApiService
) : UsersRepository {
    override fun getUsers(since: Int): Flow<Resource<List<User>>> = flow {
        try {
            val response = apiService.getUsers(since)
            if(response.isSuccessful) {
                val userDtoList = response.body()
                if (userDtoList != null) {
                    val users = userDtoList.map { it.toUser() }
                    emit(Resource.Success(users))
                } else {
                    emit(Resource.Error("Empty response body"))
                }
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)
}