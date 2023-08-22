package com.kliachenko.gitvoyager.data.repository

import androidx.paging.*
import com.kliachenko.gitvoyager.data.database.UsersDatabase
import com.kliachenko.gitvoyager.data.mapper.toUser
import com.kliachenko.gitvoyager.data.network.ApiService
import com.kliachenko.gitvoyager.data.network.UsersRemoteMediator
import com.kliachenko.gitvoyager.domain.model.User
import com.kliachenko.gitvoyager.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDb: UsersDatabase
) : UsersRepository {

    override fun getAllUsers(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
            initialLoadSize = INITIAL_PAGE_SIZE),
            remoteMediator = UsersRemoteMediator(
                usersDb = userDb,
                apiService = apiService
            ),
            pagingSourceFactory = {
                userDb.usersDao.pagingSource()
            }
        ).flow.map { pagingData ->
            pagingData.map {
                it.toUser()
            }
        }
    }

    private companion object {
        const val PAGE_SIZE = 15
        const val INITIAL_PAGE_SIZE = 20
    }
}