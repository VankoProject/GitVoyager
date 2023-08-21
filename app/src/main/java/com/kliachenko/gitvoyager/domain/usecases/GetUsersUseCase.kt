package com.kliachenko.gitvoyager.domain.usecases

import com.kliachenko.gitvoyager.domain.model.User
import com.kliachenko.gitvoyager.domain.repository.UsersRepository
import com.kliachenko.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetUsersUseCase @Inject constructor(private val repository: UsersRepository) {

    operator fun invoke(since: Int): Flow<Resource<List<User>>> =
        repository.getUsers(since)
}