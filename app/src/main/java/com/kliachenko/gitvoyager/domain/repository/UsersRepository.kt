package com.kliachenko.gitvoyager.domain.repository

import androidx.paging.PagingData
import com.kliachenko.gitvoyager.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    fun getAllUsers(): Flow<PagingData<User>>
}