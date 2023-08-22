package com.kliachenko.gitvoyager.domain.usecases

import androidx.paging.PagingData
import com.kliachenko.gitvoyager.domain.model.User
import com.kliachenko.gitvoyager.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(private val repository: UsersRepository) {

    fun getAllUsers(): Flow<PagingData<User>> {
        return repository.getAllUsers()
    }
}