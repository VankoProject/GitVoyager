package com.kliachenko.gitvoyager.domain.repository

import com.kliachenko.gitvoyager.domain.model.User
import com.kliachenko.utils.Resource
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    fun getUsers(since: Int): Flow<Resource<List<User>>>
}